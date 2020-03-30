package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;

public class GetBucketReqV2 extends AuthReq {

    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
