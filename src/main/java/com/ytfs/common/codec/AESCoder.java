package com.ytfs.common.codec;

import com.ytfs.common.conf.UserConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESCoder {

    Cipher cipher;

    public AESCoder(int mode, byte[] aeskey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec skeySpec = new SecretKeySpec(aeskey, "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, skeySpec);
    }

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
        UserConfig.AESKey = KeyStoreCoder.generateRandomKey();

        AESCoder code = new AESCoder(Cipher.ENCRYPT_MODE);

        FileInputStream is = new FileInputStream(new File("d:\\aa.jpg"));
        FileOutputStream bs = new FileOutputStream(new File("d:\\aa.jpg.ec"));
        byte[] data = new byte[8192];
        int len = 0;
        while ((len = is.read(data)) != -1) {
            byte[] data1 = code.update(data, 0, len);
            bs.write(data1);
        }
        byte[] data1 = code.doFinal();
        bs.write(data1);
        bs.close();
        is.close();
        OutputStream bss = new java.io.BufferedOutputStream(new FileOutputStream(new File("d:\\aa.0.jpg")));
        InputStream is1 = new java.io.BufferedInputStream(new FileInputStream(new File("d:\\aa.jpg")));

        long readpos = 1024 * 126 + 3;
        for (long ll = 0; ll < readpos; ll++) {
            int r = is1.read();
            bss.write(r);
        }
        is1.close();

        long startpos = readpos / 16L;
        int skipn = (int) (readpos % 16L);
        is = new FileInputStream(new File("d:\\aa.jpg.ec"));
        is.skip(startpos * 16L);
        InputStream bin = new AESDecryptInputStream(is, UserConfig.AESKey);
        if (skipn > 0) {
            bin.skip(skipn);
        }
        while ((len = bin.read(data)) != -1) {
            bss.write(data, 0, len);
        }
        bin.close();
        bss.close();

    }
}
