package com.ytfs.service.packet.user;

public class LoginReq {

    private int userId;
    private String signData;
    private int keyNumber;

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
