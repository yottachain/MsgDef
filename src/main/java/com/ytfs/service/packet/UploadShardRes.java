package com.ytfs.service.packet;

public class UploadShardRes {

    public static final int RES_OK = 0;
    public static final int RES_NETIOERR = 400;
    public static final int RES_BAD_REQUEST = 100;
    public static final int RES_NO_SPACE = 101;
    public static final int RES_VNF_EXISTS = 102;
    public static final int RES_CACHE_FILL = 105;

    private int SHARDID; //分片索引
    private int NODEID;
    private byte[] VHF;
    private String DNSIGN;

    /**
     * @return the NODEID
     */
    public int getNODEID() {
        return NODEID;
    }

    /**
     * @param NODEID the NODEID to set
     */
    public void setNODEID(int NODEID) {
        this.NODEID = NODEID;
    }

    /**
     * @return the SHARDID
     */
    public int getSHARDID() {
        return SHARDID;
    }

    /**
     * @param SHARDID the SHARDID to set
     */
    public void setSHARDID(int SHARDID) {
        this.SHARDID = SHARDID;
    }

    /**
     * @return the VHF
     */
    public byte[] getVHF() {
        return VHF;
    }

    /**
     * @param VHF the VHF to set
     */
    public void setVHF(byte[] VHF) {
        this.VHF = VHF;
    }

    /**
     * @return the DNSIGN
     */
    public String getDNSIGN() {
        return DNSIGN;
    }

    /**
     * @param DNSIGN the DNSIGN to set
     */
    public void setDNSIGN(String DNSIGN) {
        this.DNSIGN = DNSIGN;
    }

}
