package com.ytfs.service.packet.user;

public class DownloadObjectInitResp {

    private byte[][] refers;
    private long length;

    /**
     * @return the refers
     */
    public byte[][] getRefers() {
        return refers;
    }

    /**
     * @param refers the refers to set
     */
    public void setRefers(byte[][] refers) {
        this.refers = refers;
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
