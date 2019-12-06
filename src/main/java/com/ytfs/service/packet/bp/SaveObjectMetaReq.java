package com.ytfs.service.packet.bp;

import com.ytfs.service.packet.ObjectRefer;
import org.bson.types.ObjectId;

public class SaveObjectMetaReq {

    private int userID;
    private ObjectId VNU;
    private ObjectRefer refer;
    private long usedSpace;
   

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
    public long getUsedSpace() {
        return usedSpace;
    }

    /**
     * @param usedSpace the nlink to set
     */
    public void setUsedSpace(long usedSpace) {
        this.usedSpace = usedSpace;
    }

}
