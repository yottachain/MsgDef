package com.ytfs.service.packet;

import org.bson.types.ObjectId;

public class UploadObjectInitResp {

    private boolean repeat = false;
    private ObjectId VNU = null;
    private short[] blocks = null;
    private byte[] signArg;

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
    public byte[] getSignArg() {
        return signArg;
    }

    /**
     * @param signArg the signArg to set
     */
    public void setSignArg(byte[] signArg) {
        this.signArg = signArg;
    }
}
