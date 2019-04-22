package com.ytfs.service.packet;

public class UploadShardResp {

    private int RES;  //0成功  100 bad request  101  空间不足 
    private int SHARDID; //分片索引
    private long VBI;    //上传块生成的流水号
    private byte[] VHF;      //分片hash
    private byte[] USERSIGN; //用户对VHF和SHARDID,NODEID,VBI的签名

    /**
     * @return the RES
     */
    public int getRES() {
        return RES;
    }

    /**
     * @param RES the RES to set
     */
    public void setRES(int RES) {
        this.RES = RES;
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
}
