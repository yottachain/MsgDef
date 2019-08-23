package com.ytfs.service.packet.node;

public class GetNodeCapacityResp {

    private boolean writable;
    private String allocId;
       

    /**
     * @return the writable
     */
    public boolean isWritable() {
        return writable;
    }

    /**
     * @param writable the writable to set
     */
    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    /**
     * @return the allocId
     */
    public String getAllocId() {
        return allocId;
    }

    /**
     * @param allocId the allocId to set
     */
    public void setAllocId(String allocId) {
        this.allocId = allocId;
    }

}
