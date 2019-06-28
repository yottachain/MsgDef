package com.ytfs.service.packet;

public class UploadObjectEndResp {

    private byte[] signArg;
    private String contractAccount;
    private long firstCost;
    private int userid;

    
    /**
     * @return the signArg
     */
    public byte[] getSignArg() {
        return signArg;
    }

    /**
     * @param signArg the signArg to set
     */
    public void setSignArg(byte[] signArg) {
        this.signArg = signArg;
    }

    /**
     * @return the firstCost
     */
    public long getFirstCost() {
        return firstCost;
    }

    /**
     * @param firstCost the firstCost to set
     */
    public void setFirstCost(long firstCost) {
        this.firstCost = firstCost;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the contractAccount
     */
    public String getContractAccount() {
        return contractAccount;
    }

    /**
     * @param contractAccount the contractAccount to set
     */
    public void setContractAccount(String contractAccount) {
        this.contractAccount = contractAccount;
    }

}
