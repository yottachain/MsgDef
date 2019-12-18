package com.ytfs.service.packet.bp;

public class UserListReq {

    private int lastId;
    private int count;

    /**
     * @return the lastId
     */
    public int getLastId() {
        return lastId;
    }

    /**
     * @param lastId the lastId to set
     */
    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

}
