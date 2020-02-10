package com.ytfs.common.codec.lrc;

import com.sun.jna.Pointer;
import com.ytfs.common.codec.BlockEncrypted;
import com.ytfs.common.codec.Shard;
import com.ytfs.common.codec.ShardEncoder;
import static com.ytfs.common.conf.UserConfig.Default_PND;
import static com.ytfs.common.conf.UserConfig.Default_Shard_Size;
import java.io.IOException;
import java.util.Arrays;

public class ShardLRCEncoder extends ShardEncoder {

    public ShardLRCEncoder(BlockEncrypted block) {
        super(block);
    }

    @Override
    public void encode() throws IOException {
        if (!encryptedBlock.needEncode()) {
            return;
        }
        int shardsize = Default_Shard_Size - 1;
        int dataShardCount = encryptedBlock.getEncryptedBlockSize() / shardsize;
        int remainSize = encryptedBlock.getEncryptedBlockSize() % shardsize;
        if (dataShardCount == 0) {
            byte[] bs = new byte[Default_Shard_Size];
            bs[0] = (byte) 0xFF;
            System.arraycopy(encryptedBlock.getData(), 0, bs, 1, remainSize);
            Arrays.fill(bs, remainSize + 1, bs.length - 1, (byte) 0x00);
            Shard shard = new Shard(bs, sha(bs));
            for (int ii = 0; ii < Default_PND; ii++) {
                getShardList().add(shard);
            }
            encryptedBlock.setCopyMode(true);
        } else {
            int totalShardCount = dataShardCount + (remainSize > 0 ? 1 : 0);
            byte[][] out = new byte[totalShardCount][Default_Shard_Size];
            for (int ii = 0; ii < dataShardCount; ii++) {
                out[ii][0] = (byte) ii;
                System.arraycopy(encryptedBlock.getData(), ii * shardsize, out[ii], 1, shardsize);
            }
            if (remainSize > 0) {
                byte[] bs = out[dataShardCount];
                bs[0] = (byte) dataShardCount;
                System.arraycopy(encryptedBlock.getData(), dataShardCount * shardsize, bs, 1, remainSize);
                Arrays.fill(bs, remainSize + 1, bs.length - 1, (byte) 0x00);
            }
            byte[][] pout;
            Pointer[] datas = new Pointer[dataShardCount + (remainSize > 0 ? 1 : 0)];
            Pointer parityOut = null;
            try {
                for (int ii = 0; ii < datas.length; ii++) {
                    datas[ii] = MemoryCache.getShardMemory();
                    datas[ii].write(0, out[ii], 0, Default_Shard_Size);
                }
                parityOut = MemoryCache.getParityMemory();
                short res = LRCLibaray.INSTANCE.LRC_Encode(datas, (short) (datas.length), Default_Shard_Size, parityOut);
                if (res <= 0) {
                    throw new IOException("LRC encode ERR.");
                }
                pout = new byte[res][Default_Shard_Size];
                for (int ii = 0; ii < res; ii++) {
                    parityOut.read(ii * Default_Shard_Size, pout[ii], 0, Default_Shard_Size);
                }
            } finally {
                MemoryCache.freeShardMemory(datas);
                MemoryCache.freeParityMemory(parityOut);
            }
            for (byte[] out1 : out) {
                Shard shard = new Shard(out1, sha(out1));
                getShardList().add(shard);
            }
            for (byte[] out1 : pout) {
                Shard shard = new Shard(out1, sha(out1));
                getShardList().add(shard);
            }
            this.dataCount = dataShardCount + (remainSize > 0 ? 1 : 0);
        }
        makeVHB();
    }

}
