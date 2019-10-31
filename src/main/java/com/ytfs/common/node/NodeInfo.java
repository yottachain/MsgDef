package com.ytfs.common.node;

public class NodeInfo {

    private int id;
    private String peerId;
    private long sendSpotTaskTime=0;

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
     * @return the peerId
     */
    public String getPeerId() {
        return peerId;
    }

    /**
     * @param peerId the peerId to set
     */
    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    /**
     * @return the sendSpotTaskTime
     */
    public long getSendSpotTaskTime() {
        return sendSpotTaskTime;
    }

    /**
     * @param sendSpotTaskTime the sendSpotTaskTime to set
     */
    public void setSendSpotTaskTime(long sendSpotTaskTime) {
        this.sendSpotTaskTime = sendSpotTaskTime;
    }

}
