package com.ytfs.service.packet;

public class UploadObjectEndResp {

    private byte[] signArg;
    private long firstCost;

    /**
     * @return the signArg
     */
    public byte[] getSignArg() {
        return signArg;
    }

    /**
     * @param signArg the signArg to set
     */
    public void setSignArg(byte[] signArg) {
        this.signArg = signArg;
    }

    /**
     * @return the firstCost
     */
    public long getFirstCost() {
        return firstCost;
    }

    /**
     * @param firstCost the firstCost to set
     */
    public void setFirstCost(long firstCost) {
        this.firstCost = firstCost;
    }

    
}
