package com.ytfs.service.packet;

public class UploadBlockInitResp {

    private long startTime;

    public UploadBlockInitResp() {
    }

    public UploadBlockInitResp(long startTime) {
        this.startTime = startTime;
    }

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

}
