package com.ytfs.common.net;

import com.ytfs.common.SerializationUtil;
import static com.ytfs.common.ServiceErrorCode.COMM_ERROR;
import static com.ytfs.common.ServiceErrorCode.SERVER_ERROR;
import com.ytfs.common.ServiceException;
import io.yottachain.nodemgmt.core.vo.Node;
import io.yottachain.nodemgmt.core.vo.SuperNode;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import io.yottachain.p2phost.interfaces.BPNodeCallback;
import io.yottachain.p2phost.interfaces.NodeCallback;
import io.yottachain.p2phost.interfaces.UserCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import static com.ytfs.common.ServiceErrorCode.NEED_LOGIN;

public class P2PUtils {

    private static final Logger LOG = Logger.getLogger(P2PUtils.class);
    static final Map<String, P2PClient> CONNECTS = new HashMap<>();
    private static LoginCaller logincaller = null;
    static P2PClientChecker checker = null;

    public static void regLoginCaller(LoginCaller caller) {
        logincaller = caller;
    }

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
        LOG.info("NodeID:" + YottaP2P.id());
        String[] addrs = YottaP2P.addrs();
        for (int ii = 0; ii < addrs.length; ii++) {
            LOG.info("Node Addrs " + ii + ":" + addrs[ii]);
        }
        checker = new P2PClientChecker();
        checker.start();
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
        return requestBPU(obj, node, null, 3);
    }

    public static Object requestBPU(Object obj, SuperNode node, int retry) throws ServiceException {
        return requestBPU(obj, node, null, retry);
    }

    public static Object requestBPU(Object obj, SuperNode node, String log_prefix, int retry) throws ServiceException {
        String log_pre = log_prefix == null
                ? ("[" + obj.getClass().getSimpleName() + "][" + node.getId() + "]")
                : ("[" + obj.getClass().getSimpleName() + "][" + node.getId() + "][" + log_prefix + "]");
        int retryTimes = 0;
        ServiceException err = null;
        while (true) {
            try {
                if (retryTimes > 0) {
                    LOG.info(log_pre + "Retry...");
                }
                return request(obj, node.getAddrs(), node.getNodeid(), MSG_2BPU, log_pre);
            } catch (ServiceException r) {
                err = r;
                if (retryTimes >= retry) {
                    break;
                }
                if (r.getErrorCode() == NEED_LOGIN) {
                    if (logincaller == null) {
                        throw r;
                    } else {
                        logincaller.login(node);
                        continue;
                    }
                }
                if (!(r.getErrorCode() == COMM_ERROR || r.getErrorCode() == SERVER_ERROR)) {
                    throw r;
                }
                retryTimes++;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                }
            }
        }
        throw err;
    }

    public static Object requestBP(Object obj, SuperNode node) throws ServiceException {
        return requestBP(obj, node, null);
    }

    public static Object requestBP(Object obj, SuperNode node, String log_prefix) throws ServiceException {
        String log_pre = log_prefix == null
                ? ("[" + obj.getClass().getSimpleName() + "][" + node.getId() + "]")
                : ("[" + obj.getClass().getSimpleName() + "][" + node.getId() + "][" + log_prefix + "]");
        return request(obj, node.getAddrs(), node.getNodeid(), MSG_2BP, log_pre);
    }

    public static Object requestNode(Object obj, Node node) throws ServiceException {
        return requestNode(obj, node, null);
    }

    public static Object requestNode(Object obj, Node node, String log_prefix) throws ServiceException {
        String log_pre = log_prefix == null
                ? ("[" + obj.getClass().getSimpleName() + "][" + node.getId() + "]")
                : ("[" + obj.getClass().getSimpleName() + "][" + node.getId() + "][" + log_prefix + "]");
        return request(obj, node.getAddrs(), node.getNodeid(), MSG_2NODE, log_pre);
    }

    public static Object requestNode(Object obj, String peerId, int id) throws ServiceException {
        String log_pre = "[" + obj.getClass().getSimpleName() + "][" + id + "]";
        return requestNode(obj, peerId, log_pre);
    }

    public static Object requestNode(Object obj, String peerId, String log_prefix) throws ServiceException {
        byte[] data = SerializationUtil.serialize(obj);
        byte[] bs = null;
        try {                    //访问p2p网络
            bs = YottaP2P.sendToNodeMsg(peerId, data);
        } catch (Throwable e) {
            LOG.error(log_prefix + "COMM_ERROR:" + e.getMessage());
            throw new ServiceException(COMM_ERROR, e.getMessage());
        }
        Object res = SerializationUtil.deserialize(bs);
        if (res instanceof ServiceException) {
            throw (ServiceException) res;
        }
        return res;

    }

    private static Object request(Object obj, List<String> addr, String key, int type, String log_pre) throws ServiceException {
        P2PClient client = CONNECTS.get(key);
        if (client == null) {
            synchronized (CONNECTS) {
                client = CONNECTS.get(key);
                if (client == null) {
                    client = new P2PClient(key);
                    CONNECTS.put(key, client);
                }
            }
        }
        return client.request(obj, addr, type, log_pre);
    }

    public static void remove(String key) {
        synchronized (CONNECTS) {
            P2PClient client = CONNECTS.remove(key);
            if (client != null) {
                client.disconnect();
            }
        }
    }

    public static String getAddrString(List<String> ls) {
        StringBuilder res = null;
        for (String s : ls) {
            if (res == null) {
                res = (new StringBuilder("[")).append(s);
            } else {
                res.append(",").append(s);
            }
        }
        return res == null ? "" : res.append("]").toString();
    }

    /**
     * 销毁
     */
    public static void stop() {
        if (checker != null) {
            checker.interrupt();
            checker = null;
        }
        try {
            YottaP2P.close();
        } catch (P2pHostException ex) {
        }
    }
}
