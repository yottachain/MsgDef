package com.ytfs.service.packet.bp;

import org.bson.types.ObjectId;

public class ActiveCache {

    private ObjectId VNU; //upload id

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
