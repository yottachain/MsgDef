package com.ytfs.service.packet.user;

public class UploadBlockDupResp {

    private byte[][] VHB;//每个加密后的数据分片的SHA256摘要，连接在一起后再计算出的MD5摘要
    private byte[][] KED;//去重密钥
    private long startTime;

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the VHB
     */
    public byte[][] getVHB() {
        return VHB;
    }

    /**
     * @param VHB the VHB to set
     */
    public void setVHB(byte[][] VHB) {
        this.VHB = VHB;
    }

    /**
     * @return the KED
     */
    public byte[][] getKED() {
        return KED;
    }

    /**
     * @param KED the KED to set
     */
    public void setKED(byte[][] KED) {
        this.KED = KED;
    }

}
