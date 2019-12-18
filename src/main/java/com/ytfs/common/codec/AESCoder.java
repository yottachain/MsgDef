package com.ytfs.common.codec;

import com.ytfs.common.conf.UserConfig;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESCoder {

    Cipher cipher;

    //Cipher.ENCRYPT_MODE
    //Cipher.DECRYPT_MODE 
    public AESCoder(int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec skeySpec = new SecretKeySpec(UserConfig.AESKey, "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, skeySpec);
    }

    public byte[] update(byte[] bs) {
        return cipher.update(bs);
    }

    public byte[] update(byte[] bs, int off, int len) {
        return cipher.update(bs, off, len);
    }

    public byte[] doFinal(byte[] bs) throws IllegalBlockSizeException, BadPaddingException {
        return cipher.doFinal(bs);
    }

    public byte[] doFinal(byte[] bs, int off, int len) throws IllegalBlockSizeException, BadPaddingException {
        return cipher.doFinal(bs, off, len);
    }

    public byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        return cipher.doFinal();
    }

    public static void main(String[] arg) throws Exception {

        AESCoder code = new AESCoder(Cipher.ENCRYPT_MODE);

        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        byte[] data1 = code.update("aaaaaaaaaa".getBytes());
        bs.write(data1);
        byte[] data2 = code.update("bbbbbbbbbbb".getBytes());
        bs.write(data2);

        byte[] data3 = code.doFinal();
        bs.write(data3);

        AESCoder code1 = new AESCoder(Cipher.DECRYPT_MODE);
        byte[] data = code1.doFinal(bs.toByteArray());
        System.out.println(new String(data));

    }
}
