package com.ytfs.service.packet.user;

import java.util.List;

public class RegUserResp {

    private int superNodeNum;   //超级节点编号 (0-31)
    private String superNodeID;  //超级节点id !=null trim
    private List<String> superNodeAddrs;  //超级节点地址
    private int userId;
    private int keyNumber;

    /**
     * @return the superNodeNum
     */
    public int getSuperNodeNum() {
        return superNodeNum;
    }

    /**
     * @param superNodeNum the superNodeNum to set
     */
    public void setSuperNodeNum(int superNodeNum) {
        this.superNodeNum = superNodeNum;
    }

    /**
     * @return the superNodeID
     */
    public String getSuperNodeID() {
        return superNodeID;
    }

    /**
     * @param superNodeID the superNodeID to set
     */
    public void setSuperNodeID(String superNodeID) {
        this.superNodeID = superNodeID;
    }

    /**
     * @return the superNodeAddrs
     */
    public List<String> getSuperNodeAddrs() {
        return superNodeAddrs;
    }

    /**
     * @param superNodeAddrs the superNodeAddrs to set
     */
    public void setSuperNodeAddrs(List<String> superNodeAddrs) {
        this.superNodeAddrs = superNodeAddrs;
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
