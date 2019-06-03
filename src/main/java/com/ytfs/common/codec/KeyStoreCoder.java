package com.ytfs.common.codec;

import io.yottachain.p2phost.utils.Base58;
import io.yottachain.ytcrypto.YTCrypto;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyStoreCoder {

    /**
     * 生成随机密钥
     *
     * @return
     */
    public static byte[] generateRandomKey() {
        long r = Math.round(Math.random() * Long.MAX_VALUE);
        long l = System.currentTimeMillis();
        byte[] bs = new byte[]{(byte) (r >>> 56), (byte) (r >>> 48), (byte) (r >>> 40), (byte) (r >>> 32),
            (byte) (r >>> 24), (byte) (r >>> 16), (byte) (r >>> 8), (byte) (r),
            (byte) (l >>> 56), (byte) (l >>> 48), (byte) (l >>> 40), (byte) (l >>> 32),
            (byte) (l >>> 24), (byte) (l >>> 16), (byte) (l >>> 8), (byte) (l)
        };
        return generateRandomKey(bs);
    }

    public static byte[] generateRandomKey(byte[] pwd) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256, new SecureRandom(pwd));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            return enCodeFormat;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * 加密随即密钥
     *
     * @param data
     * @param kd 去重用密钥KD
     * @return ked 32字节
     */
    public static byte[] aesEncryped(byte[] data, byte[] kd) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(kd, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] bs = cipher.doFinal(data);
            byte[] res = new byte[32];
            System.arraycopy(bs, 0, res, 0, 32);
            return res;
        } catch (Exception r) {
            throw new IllegalArgumentException(r.getMessage());
        }
    }

    /**
     * 解密随即密钥
     *
     * @param data
     * @param kd 去重用密钥KD
     * @return ks 16字节
     */
    public static byte[] aesDecryped(byte[] data, byte[] kd) {
        try {
            SecretKeySpec skeySpec1 = new SecretKeySpec(kd, "AES");
            Cipher cipher1 = Cipher.getInstance("AES");
            cipher1.init(Cipher.ENCRYPT_MODE, skeySpec1);
            byte[] end = cipher1.doFinal();
            byte[] srcdata = new byte[data.length + 16];
            System.arraycopy(data, 0, srcdata, 0, data.length);
            System.arraycopy(end, 0, srcdata, data.length, 16);
            SecretKeySpec skeySpec = new SecretKeySpec(kd, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            return cipher.doFinal(srcdata);
        } catch (Exception r) {
            throw new IllegalArgumentException(r.getMessage());
        }
    }

    static String prefix = "SIG_K1_";

    public static byte[] ecdsaSign(byte[] data, String prikey) {
        try {
            String sign = YTCrypto.sign(prikey, data);
            sign = sign.substring(7);
            return Base58.decode(sign);
        } catch (Exception r) {
            throw new IllegalArgumentException(r.getMessage());
        }
    }

    public static boolean ecdsaVerify(byte[] data, byte[] sign, String pubkey) {
        try {
            String signstr = Base58.encode(sign);
            signstr = prefix + signstr;
            return YTCrypto.verify(pubkey, data, signstr);
        } catch (Exception r) {
            throw new IllegalArgumentException(r.getMessage());
        }
    }

}
