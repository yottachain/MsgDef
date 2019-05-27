package com.ytfs.service.packet.s3;

import org.bson.types.ObjectId;

import java.util.Map;

public class ListObjectReq {
    private String bucketName;

    private ObjectId startId;

    private Integer limit;


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ObjectId getStartId() {
        return startId;
    }

    public void setStartId(ObjectId startId) {
        this.startId = startId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
