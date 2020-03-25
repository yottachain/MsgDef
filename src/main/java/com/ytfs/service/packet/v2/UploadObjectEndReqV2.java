package com.ytfs.service.packet.v2;

import org.bson.types.ObjectId;

public class UploadObjectEndReqV2 extends AuthReq {

    private ObjectId VNU;
    private byte[] VHW = null;

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
}
