package com.ytfs.service.packet.bp;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class TotalResp {

    private long fileTotal = 0;
    private long spaceTotal = 0;
    private long usedspace = 0;
    private long bkTotal = 0;
    private long actualBkTotal = 0;

    public void addTotalResp(TotalResp response) {
        this.addActualBkTotal(response.actualBkTotal);
        this.addBkTotal(response.bkTotal);
        this.addFileTotal(response.fileTotal);
        this.addSpaceTotal(response.spaceTotal);
        this.addUsedspace(response.usedspace);
    }

    public void putNode(ObjectNode node) {
        node.put("fileTotal", fileTotal);
        node.put("spaceTotal", spaceTotal);
        node.put("usedspace", usedspace);
        node.put("bkTotal", bkTotal);
        node.put("actualBkTotal", actualBkTotal);
    }

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

    public void addFileTotal(long total) {
        this.fileTotal = this.fileTotal + total;
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

    public void addSpaceTotal(long total) {
        this.spaceTotal = this.spaceTotal + total;
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

    public void addUsedspace(long space) {
        this.usedspace = this.usedspace + space;
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

    public void addBkTotal(long total) {
        this.bkTotal = this.bkTotal + total;
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

    public void addActualBkTotal(long total) {
        this.actualBkTotal = this.actualBkTotal + total;
    }

}
