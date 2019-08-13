package com.ytfs.service.packet.s3;


import org.bson.types.ObjectId;

public class CopyObjectResp {
    private ObjectId bucketId;
    private String bucketName;
    private String fileName;
    private byte[] meta;
    private ObjectId versionId;
    private ObjectId fileId;

    public ObjectId getBucketId() {
        return bucketId;
    }

    public void setBucketId(ObjectId bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getMeta() {
        return meta;
    }

    public void setMeta(byte[] meta) {
        this.meta = meta;
    }

    public ObjectId getVersionId() {
        return versionId;
    }

    public void setVersionId(ObjectId versionId) {
        this.versionId = versionId;
    }

    public ObjectId getFileId() {
        return fileId;
    }

    public void setFileId(ObjectId fileId) {
        this.fileId = fileId;
    }
}
