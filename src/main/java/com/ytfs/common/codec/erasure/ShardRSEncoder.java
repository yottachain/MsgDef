package com.ytfs.common.codec.erasure;

import com.ytfs.common.codec.BlockEncrypted;
import com.ytfs.common.codec.Shard;
import com.ytfs.common.codec.ShardEncoder;
import static com.ytfs.common.conf.UserConfig.Default_PND;
import static com.ytfs.common.conf.UserConfig.Default_Shard_Size;
import java.io.IOException;
import java.util.Arrays;

public class ShardRSEncoder extends ShardEncoder {

    public ShardRSEncoder(BlockEncrypted block) {
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
            ReedSolomon reedSolomon = ReedSolomon.create(dataShardCount + (remainSize > 0 ? 1 : 0), Default_PND);
            byte[][] out = new byte[reedSolomon.getTotalShardCount()][Default_Shard_Size];
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
            for (int ii = reedSolomon.getDataShardCount(); ii < reedSolomon.getTotalShardCount(); ii++) {
                out[ii][0] = (byte) ii;
            }
            reedSolomon.encodeParity(out, 1, shardsize);
            for (byte[] out1 : out) {
                Shard shard = new Shard(out1, sha(out1));
                getShardList().add(shard);
            }
            this.dataCount = dataShardCount + (remainSize > 0 ? 1 : 0);
        }
        makeVHB();
    }
}
