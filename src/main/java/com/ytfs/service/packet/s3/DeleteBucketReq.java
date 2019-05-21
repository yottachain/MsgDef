package com.ytfs.service.packet.s3;

public class DeleteBucketReq {

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
