package com.ytfs.common.net;

import com.ytfs.common.SerializationUtil;
import static com.ytfs.common.ServiceErrorCode.COMM_ERROR;
import com.ytfs.common.ServiceException;
import static com.ytfs.common.net.P2PUtils.MSG_2BP;
import static com.ytfs.common.net.P2PUtils.MSG_2BPU;
import static com.ytfs.common.net.P2PUtils.getAddrString;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import java.util.List;
import org.apache.log4j.Logger;

public class P2PClient {

    private static final Logger LOG = Logger.getLogger(P2PClient.class);
    private String addrString;
    private final String key;
    private boolean isConnected = false;

    public P2PClient(String key) {
        this.key = key;
    }

    public Object request(Object obj, List<String> addr, int type, String log_pre) throws ServiceException {
        if (!isConnected) {
            synchronized (this) {
                if (!isConnected) {
                    String addstr = getAddrString(addr);
                    try {
                        String[] strs = new String[addr.size()];
                        strs = addr.toArray(strs);
                        YottaP2P.connect(key, strs);
                        isConnected = true;
                        addrString = addstr;
                        LOG.info(log_pre + "Connect " + addstr + " OK.");
                    } catch (P2pHostException ex) {
                        LOG.info(log_pre + "Connect " + addstr + " Err.");
                        throw new ServiceException(COMM_ERROR, ex.getMessage());
                    }
                }
            }
        }
        byte[] data = SerializationUtil.serialize(obj);
        byte[] bs = null;
        try {                    //访问p2p网络
            switch (type) {
                case MSG_2BPU:
                    bs = YottaP2P.sendToBPUMsg(key, data);
                    break;
                case MSG_2BP:
                    bs = YottaP2P.sendToBPMsg(key, data);
                    break;
                default:
                    bs = YottaP2P.sendToNodeMsg(key, data);
                    break;
            }
        } catch (Throwable e) {
            LOG.error(log_pre + "COMM_ERROR:" + addrString + e.getMessage());
            String newaddrString = getAddrString(addr);
            synchronized (this) {
                try {
                    YottaP2P.disconnect(key);
                } catch (P2pHostException r) {
                }
                isConnected = false;
            }
            if (!newaddrString.equals(addrString)) {
                LOG.info(log_pre + "Retry...");
                return request(obj, addr, type, log_pre);
            } else {
                throw new ServiceException(COMM_ERROR, e.getMessage());
            }
        }
        Object res = SerializationUtil.deserialize(bs);
        if (res instanceof ServiceException) {
            throw (ServiceException) res;
        }
        return res;
    }
}
