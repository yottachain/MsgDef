package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;
import org.bson.types.ObjectId;

public class DeleteFileReqV2 extends AuthReq {

    private String bucketName;
    private String fileName;
    private ObjectId VNU;
    private byte[] meta;

    public byte[] getMeta() {
        return meta;
    }

    public void setMeta(byte[] meta) {
        this.meta = meta;
    }

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

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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

}
