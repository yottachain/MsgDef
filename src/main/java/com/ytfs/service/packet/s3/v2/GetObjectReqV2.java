package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;

public class GetObjectReqV2 extends AuthReq {

    private String bucketName;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
