package com.ytfs.service.packet;

import org.bson.types.ObjectId;

public class GetBalanceReq {

    private long length;
    private byte[] VHW;
    private byte[] signData;
    private ObjectId VNU;

    /**
     * @return the length
     */
    public long getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * @return the VHW
     */
    public byte[] getVHW() {
        return VHW;
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
