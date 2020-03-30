package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;

public class UpdateBucketReqV2 extends AuthReq {

    private String bucketName;

    private byte[] meta;

    public byte[] getMeta() {
        return meta;
    }

    public void setMeta(byte[] meta) {
        this.meta = meta;
    }

    /**
     * @return the bucketName
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * @param bucketName the bucketName to set
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

}
