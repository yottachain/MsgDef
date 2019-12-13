package com.ytfs.common.net;

import com.ytfs.common.Function;
import com.ytfs.common.MessageFactory;
import com.ytfs.common.SerializationUtil;
import static com.ytfs.common.ServiceErrorCode.COMM_ERROR;
import com.ytfs.common.ServiceException;
import static com.ytfs.common.conf.UserConfig.CONN_EXPIRED;
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
        short id = MessageFactory.getMessageID(obj.getClass());
        byte[] msgType = new byte[2];
        Function.short2bytes(id, msgType, 0);
        byte[] data = SerializationUtil.serializeNoID(obj);
        byte[] bs = null;
        try {                    //访问p2p网络
            bs = YottaP2P.sendMsg(getKey(), msgType, data);
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
