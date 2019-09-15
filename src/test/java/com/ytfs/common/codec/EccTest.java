package com.ytfs.common.codec;

import io.jafka.jeos.util.KeyUtil;
import io.yottachain.ytcrypto.YTCrypto;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Hex;

public class EccTest {

    public static void bpsign() {
        try {
            String privateKey = "5JBpNe39ydfNM1QeDb2u2ymPUZqRnxiUGiatGH1KFZuDhiuoAbA";
            System.out.println("SuperNode privateKey:" + privateKey);
            String nodeid = "16Uiu2HAmNfU9cixngZQBxW7ftew5kSegTJQaJDVBNpGZXUoRqq88";
            System.out.println("nodeid:" + nodeid);
            long VBI = 6698593494929466116L;
            System.out.println("VBI:" + VBI);
            byte[] data1 = nodeid.getBytes("utf-8");
            ByteBuffer buf = ByteBuffer.allocate(data1.length + 8);
            buf.put(data1);
            buf.putLong(VBI);
            buf.flip();
            String ss = io.yottachain.ytcrypto.YTCrypto.sign(privateKey, buf.array());
            System.out.println("BPDSIGN:" + ss);
            byte[] signed = ss.getBytes("UTF-8");
            String signature=new String(signed,"utf-8");
            String pubkey = KeyUtil.toPublicKey(privateKey);
            pubkey = pubkey.substring(3);
            boolean b= io.yottachain.ytcrypto.YTCrypto.verify(pubkey, buf.array(), signature);
            System.out.println("verify:" + b);

        } catch (Exception r) {
        }
    }

    public static void main(String[] args) throws Exception {
        bpsign();
        //aes();
        //crype();
    }

    public static void sign() throws Exception {
        String prikey = "5KQKydL7TuRwjzaFSK4ezH9RUXWuYHW1yYDp5CmQfsfTuu9MBLZ";
        String pubkey = KeyUtil.toPublicKey(prikey);
        pubkey = pubkey.substring(3);
        byte[] data = "dsfaaaaaafgdhytjtrjrytuj".getBytes();
        String signbs = YTCrypto.sign(prikey, data);
        boolean b = YTCrypto.verify(pubkey, data, signbs);
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
