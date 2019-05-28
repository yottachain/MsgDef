package com.ytfs.service.packet.s3;

import org.bson.types.ObjectId;

public class GetObjectResp {

    private String fileName;
    private ObjectId objectId;


    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
