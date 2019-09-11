package com.ytfs.service.packet.user;

public class QueryUserReq {

    private String pubkey;
    private String username;
    private String cacheKey;
    private int UserId = -1;

    /**
     * @return the UserId
     */
    public int getUserId() {
        return UserId;
    }

    /**
     * @param UserId the UserId to set
     */
    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

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

    /**
     * @return the cacheKey
     */
    public String getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey the cacheKey to set
     */
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

}
