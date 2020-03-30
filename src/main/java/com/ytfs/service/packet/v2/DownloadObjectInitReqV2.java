package com.ytfs.service.packet.v2;

public class DownloadObjectInitReqV2 extends AuthReq {

    private byte[] VHW;

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
