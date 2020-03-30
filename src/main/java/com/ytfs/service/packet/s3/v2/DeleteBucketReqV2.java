package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;

public class DeleteBucketReqV2 extends AuthReq {

    private String bucketName;


    /**
     * @return the bucketname
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * @param bucketName the bucketname to set
     */
    public void setBucketname(String bucketName) {
        this.bucketName = bucketName;
    }


}
