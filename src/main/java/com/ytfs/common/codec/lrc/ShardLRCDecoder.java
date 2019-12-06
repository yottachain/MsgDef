package com.ytfs.common.codec.lrc;

import com.sun.jna.Pointer;
import com.ytfs.common.codec.*;
import static com.ytfs.common.conf.UserConfig.Default_Shard_Size;
import java.util.ArrayList;
import java.util.List;

public class ShardLRCDecoder {

    private final int encryptedBlockSize;
    private final int dataShardCount;
    private short handler = -1;
    private Pointer data;
    private List<Pointer> shards = new ArrayList();
    private short encodeShardCount = 0;

    public ShardLRCDecoder(int encryptedBlockSize) throws Exception {
        this.encryptedBlockSize = encryptedBlockSize;
        int shardsize = Default_Shard_Size - 1;
        int shardCount = encryptedBlockSize / shardsize;
        int remainSize = encryptedBlockSize % shardsize;
        this.dataShardCount = shardCount + (remainSize > 0 ? 1 : 0);
        data = MemoryCache.getBlockMemory();
        this.handler = LRCLibaray.INSTANCE.BeginDecode((short) dataShardCount, Default_Shard_Size, data);
        if (handler < 0) {
            MemoryCache.freeBlockMemory(data);
            throw new Exception("LRC BeginDecode ERR.");
        }
    }

    public synchronized boolean addShard(byte[] bs) throws Throwable {
        try {
            if (encodeShardCount == 0 && handler >= 0) {
                Pointer shard = MemoryCache.getShardMemory();
                shard.write(0, bs, 0, Default_Shard_Size);
                shards.add(shard);
                encodeShardCount = LRCLibaray.INSTANCE.DecodeLRC(handler, shard);
                if (encodeShardCount < 0) {
                    throw new Throwable("LRC Decode ERR.");
                } else if (encodeShardCount > 0) {
                    handler = -1;
                }
            }
            return encodeShardCount > 0;
        } catch (Throwable t) {
            free();
            throw t;
        }
    }

    public void free() {
        MemoryCache.freeBlockMemory(data);
        data = null;
        MemoryCache.freeShardMemory(shards);
        shards = null;
        if (handler >= 0) {
            LRCLibaray.INSTANCE.FreeHandle(handler);
            handler = -1;
        }
    }

    public BlockEncrypted decode() {
        try {
            byte[] out = new byte[encryptedBlockSize];
            data.read(0, out, 0, out.length);
            BlockEncrypted b = new BlockEncrypted();
            b.setData(out);
            return b;
        } finally {
            free();
        }
    }

    /**
     * @return the finished
     */
    public boolean isFinished() {
        return encodeShardCount > 0;
    }

}
