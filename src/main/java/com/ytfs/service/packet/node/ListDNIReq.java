package com.ytfs.service.packet.node;

public class ListDNIReq {

    private byte[] nextId;
    private int count;

    /**
     * @return the nextId
     */
    public byte[] getNextId() {
        return nextId;
    }

    /**
     * @param nextId the nextId to set
     */
    public void setNextId(byte[] nextId) {
        this.nextId = nextId;
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
