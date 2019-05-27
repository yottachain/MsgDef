package com.ytfs.service.packet;

public class UploadBlockSubReq {

    private UploadShardRes[] res = null;
    private long VBI;    //上传块生成的流水号

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
     * @return the res
     */
    public UploadShardRes[] getRes() {
        return res;
    }

    /**
     * @param res the res to set
     */
    public void setRes(UploadShardRes[] res) {
        this.res = res;
    }

}
