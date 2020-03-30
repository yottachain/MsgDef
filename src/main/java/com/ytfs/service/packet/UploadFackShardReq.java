package com.ytfs.service.packet;

public class UploadFackShardReq {

    private int SHARDID; //分片索引
    private int BPDID;   //超级节点ID
    private long VBI;    //上传块生成的流水号
    private byte[] BPDSIGN;  //超级节点对节点ID和VBI的签名
    private byte[] DAT;      //分片数据
    private byte[] VHF;      //分片hash
    private byte[] USERSIGN; //用户对VHF和SHARDID,NODEID,VBI的签名
    private String allocId;
    private int sleep;

    /**
     * @return the BPDID
     */
    public int getBPDID() {
        return BPDID;
    }

    /**
     * @param BPDID the BPDID to set
     */
    public void setBPDID(int BPDID) {
        this.BPDID = BPDID;
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

    /**
     * @return the BPDSIGN
     */
    public byte[] getBPDSIGN() {
        return BPDSIGN;
    }

    /**
     * @param BPDSIGN the BPDSIGN to set
     */
    public void setBPDSIGN(byte[] BPDSIGN) {
        this.BPDSIGN = BPDSIGN;
    }

    /**
     * @return the data
     */
    public byte[] getDAT() {
        return DAT;
    }

    /**
     * @param data the data to set
     */
    public void setDAT(byte[] data) {
        this.DAT = data;
    }

    /**
     * @return the VHF
     */
    public byte[] getVHF() {
        return VHF;
    }

    /**
     * @param VHF the VHF to set
     */
    public void setVHF(byte[] VHF) {
        this.VHF = VHF;
    }

    /**
     * @return the USERSIGN
     */
    public byte[] getUSERSIGN() {
        return USERSIGN;
    }

    /**
     * @param USERSIGN the USERSIGN to set
     */
    public void setUSERSIGN(byte[] USERSIGN) {
        this.USERSIGN = USERSIGN;
    }

    /**
     * @return the SHARDID
     */
    public int getSHARDID() {
        return SHARDID;
    }

    /**
     * @param SHARDID the SHARDID to set
     */
    public void setSHARDID(int SHARDID) {
        this.SHARDID = SHARDID;
    }

    /**
     * @return the allocId
     */
    public String getAllocId() {
        return allocId;
    }

    /**
     * @param allocId the allocId to set
     */
    public void setAllocId(String allocId) {
        this.allocId = allocId;
    }

    /**
     * @return the sleep
     */
    public int getSleep() {
        return sleep;
    }

    /**
     * @param sleep the sleep to set
     */
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

}
