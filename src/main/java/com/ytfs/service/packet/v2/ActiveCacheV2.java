package com.ytfs.service.packet.v2;

import org.bson.types.ObjectId;

public class ActiveCacheV2 extends AuthReq {

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
