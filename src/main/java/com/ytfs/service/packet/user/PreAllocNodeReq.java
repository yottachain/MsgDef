package com.ytfs.service.packet.user;

public class PreAllocNodeReq {

    private int count;
    private int[] excludes;

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the excludes
     */
    public int[] getExcludes() {
        return excludes;
    }

    /**
     * @param excludes the excludes to set
     */
    public void setExcludes(int[] excludes) {
        this.excludes = excludes;
    }

}
