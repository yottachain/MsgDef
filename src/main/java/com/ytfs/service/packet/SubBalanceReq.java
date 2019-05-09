package com.ytfs.service.packet;

import org.bson.types.ObjectId;

public class SubBalanceReq {

    private byte[] signData;
    private ObjectId VNU;

    /**
     * @return the signData
     */
    public byte[] getSignData() {
        return signData;
    }

    /**
     * @param signData the signData to set
     */
    public void setSignData(byte[] signData) {
        this.signData = signData;
    }

    /**
     * @return the VNU
     */
    public ObjectId getVNU() {
        return VNU;
    }

    /**
     * @param VNU the VNU to set
     */
    public void setVNU(ObjectId VNU) {
        this.VNU = VNU;
    }

}
