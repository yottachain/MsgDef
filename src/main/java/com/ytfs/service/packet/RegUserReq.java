package com.ytfs.service.packet;

public class RegUserReq {

    private String pubkey;
    private String username;

    /**
     * @return the pubkey
     */
    public String getPubkey() {
        return pubkey;
    }

    /**
     * @param pubkey the pubkey to set
     */
    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

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

}
