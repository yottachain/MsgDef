package com.ytfs.service.packet.node;

public class GetNodeCapacityReq {

    private long startTime;
    private int retryTimes;

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
     * @return the retryTimes
     */
    public int getRetryTimes() {
        return retryTimes;
    }

    /**
     * @param retryTimes the retryTimes to set
     */
    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

}
