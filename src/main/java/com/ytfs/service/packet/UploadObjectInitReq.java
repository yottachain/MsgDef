package com.ytfs.service.packet;

public class UploadObjectInitReq {

    private byte[] VHW;

    public UploadObjectInitReq() {
    }

    public UploadObjectInitReq(byte[] VHW) {
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

}
