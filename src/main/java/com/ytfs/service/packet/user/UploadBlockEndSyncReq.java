package com.ytfs.service.packet.user;

import com.ytfs.service.packet.UploadShardRes;
import java.util.List;
import org.bson.types.ObjectId;

public class UploadBlockEndSyncReq {

    private short id;
    private byte[] VHP;
    private byte[] VHB;
    private byte[] KEU;
    private byte[] KED;
    private long originalSize;  //编码前长度  6字节
    private int realSize;
    private ObjectId VNU; //upload id
    private int AR;
    private List<UploadShardRes> okList;
    private long VBI;

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
     * @return the KED
     */
    public byte[] getKED() {
        return KED;
    }

    /**
     * @param KED the KED to set
     */
    public void setKED(byte[] KED) {
        this.KED = KED;
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
     * @return the AR
     */
    public int getAR() {
        return AR;
    }

    /**
     * @param AR the AR to set
     */
    public void setAR(int AR) {
        this.AR = AR;
    }

    /**
     * @return the okList
     */
    public List<UploadShardRes> getOkList() {
        return okList;
    }

    /**
     * @param okList the okList to set
     */
    public void setOkList(List<UploadShardRes> okList) {
        this.okList = okList;
    }

    /**
     * @return the VBI
     */
    public long getVBI() {
        return VBI;
    }

    /**
     * @param VBI the VBI to set
     */
    public void setVBI(long VBI) {
        this.VBI = VBI;
    }

}
