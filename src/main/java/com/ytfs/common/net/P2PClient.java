package com.ytfs.common.net;

import com.ytfs.common.SerializationUtil;
import static com.ytfs.common.ServiceErrorCode.COMM_ERROR;
import com.ytfs.common.ServiceException;
import static com.ytfs.common.conf.UserConfig.CONN_EXPIRED;
import static com.ytfs.common.net.P2PUtils.MSG_2BP;
import static com.ytfs.common.net.P2PUtils.MSG_2BPU;
import static com.ytfs.common.net.P2PUtils.getAddrString;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;

public class P2PClient {

    private static final Logger LOG = Logger.getLogger(P2PClient.class);
    private String addrString;
    private final String key;
    private boolean isConnected = false;
    private boolean isDestroy = false;
    private final AtomicLong lasttime = new AtomicLong(System.currentTimeMillis());

    public P2PClient(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return System.currentTimeMillis() - lasttime.get() <= CONN_EXPIRED;
    }

    public Object request(Object obj, List<String> addr, int type, String log_pre) throws ServiceException {
        if (!isConnected) {
            synchronized (this) {
                if (!isConnected && !isDestroy) {
                    String addstr = getAddrString(addr);
                    try {
                        String[] strs = new String[addr.size()];
                        strs = addr.toArray(strs);
                        YottaP2P.connect(getKey(), strs);
                        isConnected = true;
                        addrString = addstr;
                    } catch (P2pHostException ex) {
                        LOG.info(log_pre + "Connect " + addstr + " Err:" + ex.getMessage());
                        throw new ServiceException(COMM_ERROR, ex.getMessage());
                    }
                }
            }
        }
        lasttime.set(System.currentTimeMillis());
        byte[] data = SerializationUtil.serialize(obj);
        byte[] bs = null;
        try {                    //访问p2p网络
            switch (type) {
                case MSG_2BPU:
                    bs = YottaP2P.sendToBPUMsg(getKey(), data);
                    break;
                case MSG_2BP:
                    bs = YottaP2P.sendToBPMsg(getKey(), data);
                    break;
                default:
                    bs = YottaP2P.sendToNodeMsg(getKey(), data);
                    break;
            }
        } catch (Throwable e) {
            LOG.error(log_pre + "COMM_ERROR:" + addrString + e.getMessage());
            synchronized (this) {
                isConnected = false;
            }
            throw new ServiceException(COMM_ERROR, e.getMessage());
        }
        Object res = SerializationUtil.deserialize(bs);
        if (res instanceof ServiceException) {
            throw (ServiceException) res;
        }
        return res;
    }

    void disconnect() {
        isDestroy = true;
        try {
            YottaP2P.disconnect(getKey());
        } catch (P2pHostException r) {
        }
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the addrString
     */
    public String getAddrs() {
        return addrString;
    }
}
