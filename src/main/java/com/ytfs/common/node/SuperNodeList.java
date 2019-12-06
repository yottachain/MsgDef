package com.ytfs.common.node;

import com.ytfs.common.Function;
import com.ytfs.common.conf.UserConfig;
import com.ytfs.common.net.P2PUtils;
import com.ytfs.service.packet.user.ListSuperNodeReq;
import com.ytfs.service.packet.user.ListSuperNodeResp;
import io.yottachain.nodemgmt.core.vo.SuperNode;
import java.math.BigInteger;
import org.apache.log4j.Logger;

public class SuperNodeList {

    static SuperNode[] superList = null;
    private static final Logger LOG = Logger.getLogger(SuperNodeList.class);
    public static boolean isServer = false;

    /**
     * 客户端获取node列表
     *
     * @return SuperNode[]
     */
    public static SuperNode[] getSuperNodeList() {
        if (superList != null) {
            return superList;
        }
        synchronized (SuperNodeList.class) {
            if (superList == null) {
                try {
                    if (isServer) {
                        superList = NodeManager.getSuperNode();
                    } else {
                        ListSuperNodeReq req = new ListSuperNodeReq();
                        ListSuperNodeResp res = (ListSuperNodeResp) P2PUtils.requestBPU(req, UserConfig.superNode);
                        superList = res.getSuperList();
                    }
                } catch (Exception ex) {
                    LOG.error("", ex);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex1) {
                    }
                }
            }
        }
        return superList;
    }

    public static int getSuperNodeCount() {
        SuperNode[] nodes = getSuperNodeList();
        return nodes.length;
    }

    /**
     * 获取数据块所属超级节点编号
     *
     * @param src
     * @return　0-32;
     */
    public static SuperNode getBlockSuperNode(byte[] src) {
        int value = (int) (((src[0] & 0xFF) << 24)
                | ((src[1] & 0xFF) << 16)
                | ((src[2] & 0xFF) << 8)
                | (src[3] & 0xFF));
        value = value & 0x0FFFF;
        SuperNode[] nodes = getSuperNodeList();
        int index = value % nodes.length;
        return nodes[index];
    }

    public static BigInteger stringToName(String str) {
        BigInteger b = BigInteger.ZERO;
        byte[] strbytes = str.getBytes();
        for (int i = 0; i < strbytes.length; i++) {
            int t = charToSymbol(strbytes[i]) & 0x1f;
            b = b.or(BigInteger.valueOf(t).shiftLeft(64 - 5 * (i + 1)));
        }
        return b;
    }

    public static int charToSymbol(byte c) {
        if (c >= 0x61 && c <= 0x7a) {
            return (c - 0x61) + 6;
        }
        if (c >= 0x31 && c <= 0x35) {
            return (c - 0x31) + 1;
        }
        return 0;
    }

    public static SuperNode getUserRegSuperNode(String str) {
        BigInteger bi = stringToName(str);
        SuperNode[] nodes = getSuperNodeList();
        BigInteger index = bi.mod(BigInteger.valueOf(nodes.length));
        return nodes[index.intValue()];
    }

    /**
     * 获取用户注册节点
     *
     * @param src
     * @return
     */
    public static SuperNode getUserRegSuperNode(byte[] src) {
        if (src.length < 8) {
            throw new IllegalArgumentException("Invalid public key.");
        }
        int value = (int) (((src[4] & 0xFF) << 24)
                | ((src[5] & 0xFF) << 16)
                | ((src[6] & 0xFF) << 8)
                | (src[7] & 0xFF));
        value = value & 0x0FFFF;
        SuperNode[] nodes = getSuperNodeList();
        int index = value % nodes.length;
        return nodes[index];
    }

    /**
     * 根据超级节点编号获取超级节点
     *
     * @param id
     * @return 0-32;
     */
    public static SuperNode getSuperNode(int id) {
        SuperNode[] nodes = getSuperNodeList();
        return nodes[id];
    }

    public static SuperNode getDNISuperNode(int id) {
        SuperNode[] nodes = getSuperNodeList();
        long uid = Function.inttolong(id);
        int index = (int) (uid % (long) nodes.length);
        return nodes[index];
    }

    /**
     * 获取管理该用户的超级节点
     *
     * @param userid
     * @return 0-32;
     */
    public static SuperNode getUserSuperNode(int userid) {
        SuperNode[] nodes = getSuperNodeList();
        long uid = Function.inttolong(userid);
        int index = (int) (uid % (long) nodes.length);
        return nodes[index];
    }
}
