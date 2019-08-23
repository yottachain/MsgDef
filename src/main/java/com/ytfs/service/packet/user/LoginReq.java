package com.ytfs.service.packet.user;

public class LoginReq {
    private String userPubkey;
    private String signData;

    /**
     * @return the userPubkey
     */
    public String getUserPubkey() {
        return userPubkey;
    }

    /**
     * @param userPubkey the userPubkey to set
     */
    public void setUserPubkey(String userPubkey) {
        this.userPubkey = userPubkey;
    }

    /**
     * @return the signData
     */
    public String getSignData() {
        return signData;
    }

    /**
     * @param signData the signData to set
     */
    public void setSignData(String signData) {
        this.signData = signData;
    }
       
}
