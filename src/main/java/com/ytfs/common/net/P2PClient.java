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
    private long connectedTime = 0;//-1:成功,
    private boolean isDestroy = false;
    private final AtomicLong lasttime = new AtomicLong(System.currentTimeMillis());

    public P2PClient(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return System.currentTimeMillis() - lasttime.get() <= CONN_EXPIRED;
    }

    public Object request(Object obj, List<String> addr, int type, String log_pre) throws ServiceException {
        lasttime.set(System.currentTimeMillis());
        if (connectedTime >= 0) {
            synchronized (this) {
                if (connectedTime >= 0 && !isDestroy) {
                    addrString = getAddrString(addr);
                    if (System.currentTimeMillis() - connectedTime < 1000 * 15) {
                        LOG.error(log_pre + addrString + " did not connect successfully 15 seconds ago");
                        throw new ServiceException(COMM_ERROR);
                    } else {
                        try {
                            String[] strs = new String[addr.size()];
                            strs = addr.toArray(strs);
                            YottaP2P.connect(getKey(), strs);
                            connectedTime = -1;
                        } catch (P2pHostException ex) {
                            connectedTime = System.currentTimeMillis();
                            LOG.error(log_pre + "Connect " + addrString + " Err:" + getErrMessage(ex));
                            throw new ServiceException(COMM_ERROR, ex.getMessage());
                        }
                    }
                }
            }
        }
        short id = MessageFactory.getMessageID(obj.getClass());
        byte[] msgType = new byte[2];
        Function.short2bytes(id, msgType, 0);
        byte[] data = SerializationUtil.serializeNoID(obj);
        byte[] bs = null;
        try {                    //访问p2p网络
            bs = YottaP2P.sendMsg(getKey(), msgType, data);
        } catch (Throwable e) {
            LOG.error(log_pre + "COMM_ERROR:" + addrString + getErrMessage(e));
            synchronized (this) {
                connectedTime = 0;
            }
            throw new ServiceException(COMM_ERROR, e.getMessage());
        }
        Object res = SerializationUtil.deserialize(bs);
        if (res instanceof ServiceException) {
            throw (ServiceException) res;
        }
        return res;
    }

    private static String getErrMessage(Throwable err) {
        Throwable t = err;
        while (t != null) {
            if (t.getMessage() == null || t.getMessage().isEmpty()) {
                t = t.getCause();
                continue;
            } else {
                return t.getMessage();
            }
        }
        return "";
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
