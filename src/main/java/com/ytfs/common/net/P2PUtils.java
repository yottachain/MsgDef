package com.ytfs.common.net;

import com.ytfs.common.SerializationUtil;
import static com.ytfs.common.ServiceErrorCode.INTERNAL_ERROR;
import static com.ytfs.common.ServiceErrorCode.SERVER_ERROR;
import com.ytfs.common.ServiceException;
import io.yottachain.nodemgmt.core.vo.Node;
import io.yottachain.nodemgmt.core.vo.SuperNode;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import io.yottachain.p2phost.interfaces.BPNodeCallback;
import io.yottachain.p2phost.interfaces.NodeCallback;
import io.yottachain.p2phost.interfaces.UserCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

public class P2PUtils {

    private static final Logger LOG = Logger.getLogger(P2PUtils.class);
    private static final List<Integer> CONNECTS = new ArrayList();

    /**
     * 初始化P2P工具
     *
     * @param port
     * @param privateKey
     * @throws java.lang.Exception
     */
    public static void start(int port, String privateKey) throws Exception {
        YottaP2P.start(port, privateKey);
        LOG.info("P2P initialization completed, port " + port);
        LOG.info("Super NodeID:" + YottaP2P.id());
        String[] addrs = YottaP2P.addrs();
        for (int ii = 0; ii < addrs.length; ii++) {
            LOG.info("Super Node Addrs " + ii + ":" + addrs[ii]);
        }
    }

    public static void register(UserCallback userCallback, BPNodeCallback bPNodeCallback, NodeCallback nodeCallback) {
        YottaP2P.registerUserCallback(userCallback);
        YottaP2P.registerBPNodeCallback(bPNodeCallback);
        YottaP2P.registerNodeCallback(nodeCallback);
    }

    public static final int MSG_2BPU = 0;
    public static final int MSG_2BP = 1;
    public static final int MSG_2NODE = 2;

    public static Object requestBPU(Object obj, SuperNode node) throws ServiceException {
        ServiceException err = null;
        for (int ii = 0; ii < 5; ii++) {
            try {
                return request(obj, node.getAddrs(), node.getNodeid(), MSG_2BPU, node.getId() * -1);
            } catch (ServiceException r) {
                if (r.getErrorCode() != SERVER_ERROR) {
                    throw r;
                }
                err = r;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
            }
        }
        throw err;
    }

    public static Object requestBP(Object obj, SuperNode node) throws ServiceException {
        ServiceException err = null;
        for (int ii = 0; ii < 5; ii++) {
            try {
                return request(obj, node.getAddrs(), node.getNodeid(), MSG_2BP, node.getId() * -1);
            } catch (ServiceException r) {
                if (r.getErrorCode() != SERVER_ERROR) {
                    throw r;
                }
                err = r;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
            }
        }
        throw err;
    }

    public static Object requestNode(Object obj, Node node) throws ServiceException {
        return request(obj, node.getAddrs(), node.getNodeid(), MSG_2NODE, node.getId());
    }

    public static Object request(Object obj, List<String> addr, String key, int type, int id) throws ServiceException {
        synchronized (CONNECTS) {
            if (!CONNECTS.contains(id)) {
                try {
                    String[] strs = new String[addr.size()];
                    strs = addr.toArray(strs);
                    YottaP2P.connect(key, strs);
                } catch (P2pHostException ex) {
                    LOG.info("Connect " + toString(addr) + " Err.");
                    throw new ServiceException(INTERNAL_ERROR, ex.getMessage());
                }
                CONNECTS.add(id);
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
            LOG.error("INTERNAL_ERROR:" + toString(addr));
            synchronized (CONNECTS) {
                CONNECTS.remove(Integer.valueOf(id));
            }
            try {
                YottaP2P.disconnect(key);
            } catch (P2pHostException ex) {
            }
            throw new ServiceException(INTERNAL_ERROR, e.getMessage());
        }
        Object res = SerializationUtil.deserialize(bs);
        if (res instanceof ServiceException) {
            throw (ServiceException) res;
        }
        return res;
    }

    private static String toString(List<String> ls) {
        String res = null;
        for (String s : ls) {
            if (res == null) {
                res = "[" + s;
            } else {
                res = res + "," + s;
            }
        }
        return res + "]";
    }

    /**
     * 销毁
     */
    public static void stop() {
        try {
            YottaP2P.close();
        } catch (P2pHostException ex) {
        }
    }
}
