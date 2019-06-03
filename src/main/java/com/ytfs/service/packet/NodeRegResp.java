package com.ytfs.service.packet;

public class NodeRegResp {

    private int id;
    private long assignedSpace;

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

}
