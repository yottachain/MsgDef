package com.ytfs.service.packet.s3;

import org.bson.types.ObjectId;

public class ListObjectReq {

    private String bucketName;

    private ObjectId startId;

    private Integer limit;

    private String fileName;

    private String prefix;

    private boolean version;

    private ObjectId nextVersionId;

    private boolean compress = false;

    public ObjectId getNextVersionId() {
        return nextVersionId;
    }

    public void setNextVersionId(ObjectId nextVersionId) {
        this.nextVersionId = nextVersionId;
    }

    public boolean isVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
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

    /**
     * @return the compress
     */
    public boolean isCompress() {
        return compress;
    }

    /**
     * @param compress the compress to set
     */
    public void setCompress(boolean compress) {
        this.compress = compress;
    }

}
