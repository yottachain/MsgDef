package com.ytfs.service.packet;

public class NodeRegResp {

    private int id;
    private long assignedSpace;
    private String relayUrl;
    

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
     * @return the assignedSpace
     */
    public long getAssignedSpace() {
        return assignedSpace;
    }

    /**
     * @param assignedSpace the assignedSpace to set
     */
    public void setAssignedSpace(long assignedSpace) {
        this.assignedSpace = assignedSpace;
    }

    /**
     * @return the relayUrl
     */
    public String getRelayUrl() {
        return relayUrl;
    }

    /**
     * @param relayUrl the relayUrl to set
     */
    public void setRelayUrl(String relayUrl) {
        this.relayUrl = relayUrl;
    }

}
