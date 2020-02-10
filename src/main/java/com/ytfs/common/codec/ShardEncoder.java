package com.ytfs.common.codec;

import static com.ytfs.common.conf.UserConfig.Default_Shard_Size;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public abstract class ShardEncoder {

    public static final int AR_DB_MODE = 0;
    public static final int AR_COPY_MODE = -2;
    public static final int AR_RS_MODE = -1;

    protected final BlockEncrypted encryptedBlock;
    protected final List<Shard> shardList = new ArrayList();
    protected int dataCount = 0;
    private byte[] VHB = null;

    public ShardEncoder(BlockEncrypted encryptedBlock) {
        this.encryptedBlock = encryptedBlock;
    }

    public long getLength() {
        if (isCopyMode()) {
            return Default_Shard_Size;
        } else {
            return (long) shardList.size() * (long) Default_Shard_Size;
        }
    }

    public abstract void encode() throws IOException;

    public boolean needEncode() {
        return encryptedBlock.needEncode();
    }

    /**
     * @return the copyMode
     */
    public boolean isCopyMode() {
        return encryptedBlock.isCopyMode();
    }

    /**
     * @return the shardList
     */
    public final List<Shard> getShardList() {
        return shardList;
    }

    public final int getDataCount() {
        return dataCount;
    }

    public static byte[] sha(byte[] data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(data);
        } catch (Exception r) {
            throw new IllegalArgumentException(r.getMessage());
        }
    }

    public final byte[] makeVHB() {
        if (VHB == null) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                if (!shardList.get(0).isCopyShard()) {
                    for (Shard s : shardList) {
                        md5.update(s.getVHF());
                    }
                } else {
                    md5.update(shardList.get(0).getVHF());
                }
                VHB = md5.digest();
            } catch (Exception r) {
                throw new IllegalArgumentException(r.getMessage());
            }
        }
        return VHB;
    }
}
