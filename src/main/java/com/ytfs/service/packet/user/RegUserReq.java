package com.ytfs.service.packet.user;

public class RegUserReq {

    private byte[] signdata;
    private String pubKey;
    private String username;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the signdata
     */
    public byte[] getSigndata() {
        return signdata;
    }

    /**
     * @param signdata the signdata to set
     */
    public void setSigndata(byte[] signdata) {
        this.signdata = signdata;
    }

    /**
     * @return the pubKey
     */
    public String getPubKey() {
        return pubKey;
    }

    /**
     * @param pubKey the pubKey to set
     */
    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

}
