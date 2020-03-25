package com.ytfs.service.packet.v2;

public class DownloadBlockInitReqV2 extends AuthReq {

    private long VBI;

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
