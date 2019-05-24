package com.ytfs.common.codec;

import io.jafka.jeos.util.Base58;
import io.jafka.jeos.util.KeyUtil;
import org.apache.commons.codec.binary.Hex;

public class EccTest {

    public static void main(String[] args) throws Exception {
        //aes();
        //crype();
        sign();
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

    public static void crype() throws Exception {
        String prikey = "5KQKydL7TuRwjzaFSK4ezH9RUXWuYHW1yYDp5CmQfsfTuu9MBLZ";
        byte[] kusp = Base58.decode(prikey);//si钥    
        String pubkey = KeyUtil.toPublicKey(prikey);
        pubkey = pubkey.substring(3);
        System.out.println(prikey);
        System.out.println(pubkey);
        byte[] kuep = Base58.decode(pubkey);//gong钥    
        String sss = "dsfaaaaaafgdhytjtrjrytuj";
        System.out.println(sss);
        byte[] data = KeyStoreCoder.eccEncryped(sss.getBytes(), kuep);
        byte[] bs = KeyStoreCoder.eccDecryped(data, kusp);
        System.out.println(new String(bs));
    }

}
