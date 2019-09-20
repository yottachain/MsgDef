package com.ytfs.service.packet.user;

import org.bson.types.ObjectId;

public class UploadObjectInitResp {

    private boolean repeat = false;
    private ObjectId VNU = null;
    private short[] blocks = null;
    private String signArg;
    private long stamp;

    public UploadObjectInitResp() {

    }

    public UploadObjectInitResp(boolean repeat) {
        this.repeat = repeat;
    }

    public UploadObjectInitResp(boolean repeat, ObjectId VNU) {
        this.repeat = repeat;
        this.VNU = VNU;
    }

    /**
     * @return the repeat
     */
    public boolean isRepeat() {
        return repeat;
    }

    /**
     * @param repeat the repeat to set
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
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

    /**
     * @return the blocks
     */
    public short[] getBlocks() {
        return blocks;
    }

    /**
     * @param blocks the blocks to set
     */
    public void setBlocks(short[] blocks) {
        this.blocks = blocks;
    }

    /**
     * @return the signArg
     */
    public String getSignArg() {
        return signArg;
    }

    /**
     * @param signArg the signArg to set
     */
    public void setSignArg(String signArg) {
        this.signArg = signArg;
    }

    /**
     * @return the stamp
     */
    public long getStamp() {
        return stamp;
    }

    /**
     * @param stamp the stamp to set
     */
    public void setStamp(long stamp) {
        this.stamp = stamp;
    }
}
