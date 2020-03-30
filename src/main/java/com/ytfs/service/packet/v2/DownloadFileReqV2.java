package com.ytfs.service.packet.v2;

import org.bson.types.ObjectId;

public class DownloadFileReqV2 extends AuthReq {

    private String bucketname;
    private String fileName;
    private ObjectId versionId;

    public ObjectId getVersionId() {
        return versionId;
    }

    public void setVersionId(ObjectId versionId) {
        this.versionId = versionId;
    }

    /**
     * @return the bucketname
     */
    public String getBucketname() {
        return bucketname;
    }

    /**
     * @param bucketname the bucketname to set
     */
    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
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

}
