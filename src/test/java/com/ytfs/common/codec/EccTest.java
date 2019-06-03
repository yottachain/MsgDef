package com.ytfs.common.codec;

import io.jafka.jeos.util.KeyUtil;
import org.apache.commons.codec.binary.Hex;

public class EccTest {

    public static void main(String[] args) throws Exception {
 
        //aes();
        //crype();
   
    }

    public static void sign() throws Exception {
        String prikey = "5KQKydL7TuRwjzaFSK4ezH9RUXWuYHW1yYDp5CmQfsfTuu9MBLZ";
        String pubkey = KeyUtil.toPublicKey(prikey);
        pubkey = pubkey.substring(3);
        byte[] data = "dsfaaaaaafgdhytjtrjrytuj".getBytes();
        byte[] signbs = KeyStoreCoder.ecdsaSign(data, prikey);
        boolean b = KeyStoreCoder.ecdsaVerify(data, signbs, pubkey);
        System.out.println(b);
    }

    public static void aes() throws Exception {
        byte[] ed = KeyStoreCoder.generateRandomKey();
        Block b = new Block("sdsfagergwhge6h56".getBytes());
        b.calculate();
        System.out.println(Hex.encodeHex(ed));
        byte[] bss = KeyStoreCoder.aesEncryped(ed, b.getKD());
        System.out.println(Hex.encodeHex(bss));
        byte[] bss1 = KeyStoreCoder.aesDecryped(bss, b.getKD());
        System.out.println(Hex.encodeHex(bss1));
    }

}
