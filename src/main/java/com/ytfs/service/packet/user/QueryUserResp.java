package com.ytfs.service.packet.user;

public class QueryUserResp {

    private int userId;
    private int keyNumber;

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the keyNumber
     */
    public int getKeyNumber() {
        return keyNumber;
    }

    /**
     * @param keyNumber the keyNumber to set
     */
    public void setKeyNumber(int keyNumber) {
        this.keyNumber = keyNumber;
    }

}
