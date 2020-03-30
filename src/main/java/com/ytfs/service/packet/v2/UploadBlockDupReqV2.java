package com.ytfs.service.packet.v2;

import org.bson.types.ObjectId;

public class UploadBlockDupReqV2 extends AuthReq {

    private short id;
    private ObjectId VNU;
    private byte[] VHP;
    private byte[] VHB;
    private byte[] KEU;
    private long originalSize;  //编码前长度  6字节
    private int realSize;  //实际长度    3字节   

    /**
     * @return the id
     */
    public short getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(short id) {
        this.id = id;
    }

    /**
     * @return the VHP
     */
    public byte[] getVHP() {
        return VHP;
    }

    /**
     * @param VHP the VHP to set
     */
    public void setVHP(byte[] VHP) {
        this.VHP = VHP;
    }

    /**
     * @return the VHB
     */
    public byte[] getVHB() {
        return VHB;
    }

    /**
     * @param VHB the VHB to set
     */
    public void setVHB(byte[] VHB) {
        this.VHB = VHB;
    }

    /**
     * @return the KEU
     */
    public byte[] getKEU() {
        return KEU;
    }

    /**
     * @param KEU the KEU to set
     */
    public void setKEU(byte[] KEU) {
        this.KEU = KEU;
    }

    /**
     * @return the originalSize
     */
    public long getOriginalSize() {
        return originalSize;
    }

    /**
     * @param originalSize the originalSize to set
     */
    public void setOriginalSize(long originalSize) {
        this.originalSize = originalSize;
    }

    /**
     * @return the realSize
     */
    public int getRealSize() {
        return realSize;
    }

    /**
     * @param realSize the realSize to set
     */
    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }

    /**
     * @return the VUN
     */
    public ObjectId getVNU() {
        return VNU;
    }

    /**
     * @param VUN the VUN to set
     */
    public void setVNU(ObjectId VUN) {
        this.VNU = VUN;
    }

}
