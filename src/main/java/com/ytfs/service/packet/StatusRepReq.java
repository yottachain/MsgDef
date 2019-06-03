package com.ytfs.service.packet;

import java.util.List;

public class StatusRepReq {

    private int id;
    private int cpu;
    private int memory;
    private int bandwidth;
    private long maxDataSpace;
    private long assignedSpace;
    private long usedSpace;
    private List<String> addrs;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cpu
     */
    public int getCpu() {
        return cpu;
    }

    /**
     * @param cpu the cpu to set
     */
    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the memory
     */
    public int getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }

    /**
     * @return the bandwidth
     */
    public int getBandwidth() {
        return bandwidth;
    }

    /**
     * @param bandwidth the bandwidth to set
     */
    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * @return the maxDataSpace
     */
    public long getMaxDataSpace() {
        return maxDataSpace;
    }

    /**
     * @param maxDataSpace the maxDataSpace to set
     */
    public void setMaxDataSpace(long maxDataSpace) {
        this.maxDataSpace = maxDataSpace;
    }

    /**
     * @return the assignedSpace
     */
    public long getAssignedSpace() {
        return assignedSpace;
    }

    /**
     * @param assignedSpace the assignedSpace to set
     */
    public void setAssignedSpace(long assignedSpace) {
        this.assignedSpace = assignedSpace;
    }

    /**
     * @return the usedSpace
     */
    public long getUsedSpace() {
        return usedSpace;
    }

    /**
     * @param usedSpace the usedSpace to set
     */
    public void setUsedSpace(long usedSpace) {
        this.usedSpace = usedSpace;
    }

    /**
     * @return the addrs
     */
    public List<String> getAddrs() {
        return addrs;
    }

    /**
     * @param addrs the addrs to set
     */
    public void setAddrs(List<String> addrs) {
        this.addrs = addrs;
    }

}
