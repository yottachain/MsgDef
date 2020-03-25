package com.ytfs.service.packet.v2;

public class UploadObjectInitReqV2 extends AuthReq {

    private byte[] VHW;
    private long length;

    public UploadObjectInitReqV2() {
    }

    public UploadObjectInitReqV2(byte[] VHW) {
        this.VHW = VHW;
    }

    /**
     * @return the VHW
     */
    public byte[] getVHW() {
        return VHW;
    }

    /**
     * @param VHW the VHW to set
     */
    public void setVHW(byte[] VHW) {
        this.VHW = VHW;
    }

    /**
     * @return the length
     */
    public long getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }

}
