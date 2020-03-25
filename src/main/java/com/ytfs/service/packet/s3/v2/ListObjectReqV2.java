package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;
import org.bson.types.ObjectId;

public class ListObjectReqV2 extends AuthReq {

    private String bucketName;

    private ObjectId startId;

    private Integer limit;

    private String fileName;

    private String prefix;

    private boolean isVersion;

    private ObjectId nextVersionId;

    public ObjectId getNextVersionId() {
        return nextVersionId;
    }

    public void setNextVersionId(ObjectId nextVersionId) {
        this.nextVersionId = nextVersionId;
    }

    public boolean isVersion() {
        return isVersion;
    }

    public void setVersion(boolean version) {
        isVersion = version;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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
