package com.ytfs.common.codec;

import com.ytfs.common.codec.BlockAESEncryptor;
import com.ytfs.common.codec.ShardRSEncoder;
import com.ytfs.common.codec.BlockAESDecryptor;
import com.ytfs.common.codec.BlockEncrypted;
import com.ytfs.common.codec.KeyStoreCoder;
import com.ytfs.common.codec.ShardRSDecoder;
import com.ytfs.common.codec.Shard;
import com.ytfs.common.codec.Block;
import java.security.MessageDigest;
import java.util.List;
import org.apache.commons.codec.binary.Hex;

public class TestShardCoder {

    private static byte[] key = KeyStoreCoder.generateRandomKey();

    public static void main(String[] args) throws Exception {
        //middleBlock();
        smallBlock();
    }

    private static void middleBlock() throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        key = sha256.digest(key);

        Block block = new Block("d:\\20190701170156.png");
        block.load();

        BlockAESEncryptor aes = new BlockAESEncryptor(block, key);
        aes.encrypt();
        int encryptedBlockSize = aes.getBlockEncrypted().getEncryptedBlockSize();

        ShardRSEncoder encoder = new ShardRSEncoder(aes.getBlockEncrypted());
        encoder.encode();

        List<Shard> shards = encoder.getShardList();

        deleteDataShard(shards);
        deleteParityShard(shards);

        ShardRSDecoder decoder = new ShardRSDecoder(shards, encryptedBlockSize);
        BlockEncrypted b = decoder.decode();

        BlockAESDecryptor aesdecoder = new BlockAESDecryptor(b.getData(),   key);
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
