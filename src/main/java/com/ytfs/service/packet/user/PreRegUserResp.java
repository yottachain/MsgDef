package com.ytfs.service.packet.user;

public class PreRegUserResp {

    private byte[] signArg;
    private String contractAccount;
   

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
