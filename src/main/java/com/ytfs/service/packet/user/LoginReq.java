package com.ytfs.service.packet.user;

public class LoginReq {

    private int userId;
    private String signData;

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

}
