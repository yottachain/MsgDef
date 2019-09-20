package com.ytfs.service.packet.user;

import org.bson.types.ObjectId;

public class UploadObjectEndReq {

    private ObjectId VNU;
    private byte[] VHW = null;
    private byte[] signData;

    /**
     * @return the VHW
     */
    public byte[] getVHW() {
        return VHW;
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

    /**
     * @param VHW the VHW to set
     */
    public void setVHW(byte[] VHW) {
        this.VHW = VHW;
    }

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
}
