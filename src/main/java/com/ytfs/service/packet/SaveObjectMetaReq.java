package com.ytfs.service.packet;

import org.bson.types.ObjectId;

public class SaveObjectMetaReq {

    private int userID;
    private ObjectId VNU;
    private ObjectRefer refer;
    private long nlink;
   

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

    /**
     * @return the refer
     */
    public ObjectRefer getRefer() {
        return refer;
    }

    /**
     * @param refer the refer to set
     */
    public void setRefer(ObjectRefer refer) {
        this.refer = refer;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the nlink
     */
    public long getNlink() {
        return nlink;
    }

    /**
     * @param nlink the nlink to set
     */
    public void setNlink(long nlink) {
        this.nlink = nlink;
    }

}
