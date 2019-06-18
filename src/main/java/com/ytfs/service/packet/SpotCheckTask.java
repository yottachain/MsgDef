package com.ytfs.service.packet;

public class SpotCheckTask {

    private int id;
    private String nodeId;
    private String addr;
    private byte[] VHF;

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
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @return the VHF
     */
    public byte[] getVHF() {
        return VHF;
    }

    /**
     * @param VHF the VHF to set
     */
    public void setVHF(byte[] VHF) {
        this.VHF = VHF;
    }
}
