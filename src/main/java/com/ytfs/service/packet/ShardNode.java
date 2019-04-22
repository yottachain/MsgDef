package com.ytfs.service.packet;

import io.yottachain.nodemgmt.core.vo.Node;
import java.util.Arrays;

public class ShardNode {

    //分片索引
    private int shardid;
    //节点ID
    private int nodeId;
    //地址列表
    private String[] addr;
    //加密或解签用公钥
    private String key;
    //超级节点签名
    private byte[] sign;

    public ShardNode() {
    }

    public ShardNode(Node node) {
        this.nodeId = node.getId();
        String[] addrs = new String[node.getAddrs().size()];
        this.addr = node.getAddrs().toArray(addrs);
        this.key = node.getNodeid();
    }

    public ShardNode(int id, Node node) {
        this(node);
        this.shardid = id;
    }

    /**
     * @return the shardid
     */
    public int getShardid() {
        return shardid;
    }

    /**
     * @param shardid the shardid to set
     */
    public void setShardid(int shardid) {
        this.shardid = shardid;
    }

    /**
     * @return the nodeId
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the addr
     */
    public String[] getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String[] addr) {
        this.addr = addr;
    }

    /**
     * @return the pubkey
     */
    public String getKey() {
        return key;
    }

    /**
     * @param pubkey the pubkey to set
     */
    public void setKey(String pubkey) {
        this.key = pubkey;
    }

    /**
     * @return the sign
     */
    public byte[] getSign() {
        return sign;
    }

    /**
     * @param sign the sign to set
     */
    public void setSign(byte[] sign) {
        this.sign = sign;
    }

    public Node getNode() {
        Node node = new Node(this.nodeId, null, null, null);
        node.setNodeid(key);
        node.setAddrs(Arrays.asList(addr));
        return node;
    }
}
