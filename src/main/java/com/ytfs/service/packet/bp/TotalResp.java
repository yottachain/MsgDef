package com.ytfs.service.packet.bp;

public class TotalResp {

    private long fileTotal;
    private long spaceTotal;
    private long usedspace;
    private long bkTotal;
    private long actualBkTotal;

    /**
     * @return the fileTotal
     */
    public long getFileTotal() {
        return fileTotal;
    }

    /**
     * @param fileTotal the fileTotal to set
     */
    public void setFileTotal(long fileTotal) {
        this.fileTotal = fileTotal;
    }

    /**
     * @return the spaceTotal
     */
    public long getSpaceTotal() {
        return spaceTotal;
    }

    /**
     * @param spaceTotal the spaceTotal to set
     */
    public void setSpaceTotal(long spaceTotal) {
        this.spaceTotal = spaceTotal;
    }

    /**
     * @return the usedspace
     */
    public long getUsedspace() {
        return usedspace;
    }

    /**
     * @param usedspace the usedspace to set
     */
    public void setUsedspace(long usedspace) {
        this.usedspace = usedspace;
    }

    /**
     * @return the bkTotal
     */
    public long getBkTotal() {
        return bkTotal;
    }

    /**
     * @param bkTotal the bkTotal to set
     */
    public void setBkTotal(long bkTotal) {
        this.bkTotal = bkTotal;
    }

    /**
     * @return the actualBkTotal
     */
    public long getActualBkTotal() {
        return actualBkTotal;
    }

    /**
     * @param actualBkTotal the actualBkTotal to set
     */
    public void setActualBkTotal(long actualBkTotal) {
        this.actualBkTotal = actualBkTotal;
    }

}
