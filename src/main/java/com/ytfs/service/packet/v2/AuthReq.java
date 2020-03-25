package com.ytfs.service.packet.v2;

import io.yottachain.p2phost.utils.Base58;
import io.yottachain.ytcrypto.core.exception.YTCryptoException;
import java.nio.charset.Charset;

public class AuthReq {

    private int userId;
    private String signData;
    private int keyNumber;

    public final void fill(int userId, int keyNumber, String privateKey) {
        this.userId = userId;
        this.keyNumber = keyNumber;
        String data = Integer.toString(userId) + Integer.toString(keyNumber);
        byte[] signdata = data.getBytes(Charset.forName("UTF-8"));
        String sign = null;
        try {
            sign = io.yottachain.ytcrypto.YTCrypto.sign(privateKey, signdata);
        } catch (YTCryptoException ex) {
            throw new RuntimeException(ex);
        }
        this.signData = sign;
    }

    public final boolean verify(byte[] pubkey) {
        String data = Integer.toString(userId) + Integer.toString(keyNumber);
        try {
            String pkey = Base58.encode(pubkey);
            return io.yottachain.ytcrypto.YTCrypto.verify(pkey, data.getBytes(Charset.forName("UTF-8")), signData);
        } catch (YTCryptoException ex) {
            return false;
        }
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
