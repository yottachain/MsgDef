package com.ytfs.service.packet.user;

import io.yottachain.nodemgmt.core.vo.Node;
import java.util.List;

public class PreAllocNode {

    private int id;
    private String nodeid;
    private String pubkey;
    private List<String> addrs;
    private long timestamp;
    private String sign;

    public PreAllocNode() {
    }

    public PreAllocNode(Node node) {
        this.id = node.getId();
        this.nodeid = node.getNodeid();
        this.pubkey = node.getPubkey();
        this.addrs = node.getAddrs();
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nodeid
     */
    public String getNodeid() {
        return nodeid;
    }

    /**
     * @param nodeid the nodeid to set
     */
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    /**
     * @return the pubkey
     */
    public String getPubkey() {
        return pubkey;
    }

    /**
     * @param pubkey the pubkey to set
     */
    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    /**
     * @return the addrs
     */
    public List<String> getAddrs() {
        return addrs;
    }

    /**
     * @param addrs the addrs to set
     */
    public void setAddrs(List<String> addrs) {
        this.addrs = addrs;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

}
