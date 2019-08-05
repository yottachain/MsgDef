package com.ytfs.common.codec;

import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.List;
import org.apache.commons.codec.binary.Hex;

public class TestShardCoder {

    private static byte[] key = KeyStoreCoder.generateRandomKey();

    public static void main(String[] args) throws Exception {
        middleBlock();
        //smallBlock();
    }

    private static void middleBlock() throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        key = sha256.digest(key);

        Block block = new Block("d:\\test.gif");
        block.load();

        BlockAESEncryptor aes = new BlockAESEncryptor(block, key);
        aes.encrypt();
        int encryptedBlockSize = aes.getBlockEncrypted().getEncryptedBlockSize();

        FileOutputStream fos = new FileOutputStream("d:/test.dat");
        fos.write(aes.getBlockEncrypted().getData());
        fos.close();

        ShardRSEncoder encoder = new ShardRSEncoder(aes.getBlockEncrypted());
        encoder.encode();

        List<Shard> shards = encoder.getShardList();
        for (int ii = 0; ii < shards.size(); ii++) {
            FileOutputStream foss = new FileOutputStream("d:/test" + ii + ".dat");
            foss.write(shards.get(ii).getData());
            foss.close();
        }

        deleteDataShard(shards);
        deleteParityShard(shards);
        ShardRSDecoder decoder = new ShardRSDecoder(shards, encryptedBlockSize);
        BlockEncrypted b = decoder.decode();

        FileOutputStream fos1 = new FileOutputStream("d:/test.dat.bak");
        fos1.write(b.getData());
        fos1.close();

        BlockAESDecryptor aesdecoder = new BlockAESDecryptor(b.getData(), key);
        aesdecoder.decrypt();

        block = new Block(aesdecoder.getSrcData());
        block.save("d:\\20190701170156.0.png");

    }

    private static void smallBlock() throws Exception {
        Block block = new Block("d:\\LICENSE");
        block.load();
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        key = sha256.digest("123456".getBytes());
        System.out.println(Hex.encodeHexString(block.getData()));
        BlockAESEncryptor aes = new BlockAESEncryptor(block, key);
        aes.encrypt();

        BlockEncrypted b = aes.getBlockEncrypted();
        System.out.println(Hex.encodeHexString(b.getData()));
        int encryptedBlockSize = aes.getBlockEncrypted().getEncryptedBlockSize();

        ShardRSEncoder encoder = new ShardRSEncoder(aes.getBlockEncrypted());
        encoder.encode();
        List<Shard> shards = encoder.getShardList();
        deleteDataShard(shards);
        deleteParityShard(shards);
        ShardRSDecoder decoder = new ShardRSDecoder(shards, encryptedBlockSize);
        b = decoder.decode();
        BlockAESDecryptor aesdecoder = new BlockAESDecryptor(b.getData(), key);
        aesdecoder.decrypt();
        block = new Block(aesdecoder.getSrcData());
        block.save("d:\\LICENSE.txt");
    }

    private static void deleteDataShard(List<Shard> shards) {
        shards.remove(2);
        shards.remove(5);
        shards.remove(7);
        shards.remove(13);
        shards.remove(11);
        shards.remove(9);
        shards.remove(8);
        shards.remove(18);
        shards.remove(20);
    }

    private static void deleteParityShard(List<Shard> shards) {
        shards.remove(shards.size() - 2);
        shards.remove(shards.size() - 2);
        shards.remove(shards.size() - 2);
    }
}
