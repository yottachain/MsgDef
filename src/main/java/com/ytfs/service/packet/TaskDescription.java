package com.ytfs.service.packet;

public class TaskDescription {

    private long id;
    private byte[][] dataHash;
    private byte[][] parityHash;
    private P2PLocation[] locations;
    private int[] recoverId;

    public static class P2PLocation {

        private String nodeId;
        private String[] addrs;

        /**
         * @return the nodeId
         */
        public String getNodeId() {
            return nodeId;
        }

        /**
         * @param nodeId the nodeId to set
         */
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        /**
         * @return the addrs
         */
        public String[] getAddrs() {
            return addrs;
        }

        /**
         * @param addrs the addrs to set
         */
        public void setAddrs(String[] addrs) {
            this.addrs = addrs;
        }

    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the dataHash
     */
    public byte[][] getDataHash() {
        return dataHash;
    }

    /**
     * @param dataHash the dataHash to set
     */
    public void setDataHash(byte[][] dataHash) {
        this.dataHash = dataHash;
    }

    /**
     * @return the parityHash
     */
    public byte[][] getParityHash() {
        return parityHash;
    }

    /**
     * @param parityHash the parityHash to set
     */
    public void setParityHash(byte[][] parityHash) {
        this.parityHash = parityHash;
    }

    /**
     * @return the locations
     */
    public P2PLocation[] getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(P2PLocation[] locations) {
        this.locations = locations;
    }

    /**
     * @return the recoverId
     */
    public int[] getRecoverId() {
        return recoverId;
    }

    /**
     * @param recoverId the recoverId to set
     */
    public void setRecoverId(int[] recoverId) {
        this.recoverId = recoverId;
    }
}
