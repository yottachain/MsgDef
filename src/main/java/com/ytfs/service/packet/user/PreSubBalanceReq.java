package com.ytfs.service.packet.user;

import org.bson.types.ObjectId;

public class PreSubBalanceReq {

    private ObjectId VNU;

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
