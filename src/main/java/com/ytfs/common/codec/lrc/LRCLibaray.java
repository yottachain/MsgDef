package com.ytfs.common.codec.lrc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public abstract interface LRCLibaray extends Library {

    public static final LRCLibaray INSTANCE = (LRCLibaray) Native.load(Platform.isWindows() ? "/com/ytfs/common/codec/lrc/lrc.dll" : "/com/ytfs/common/codec/lrc/lrc.so", LRCLibaray.class);

    public abstract short LRC_Initial(short globalRecoveryCount, short maxHandles);

    public abstract short LRC_Encode(Pointer[] originalData, short originalCount, long shardSize, Pointer recoveryData);

    public abstract short LRC_BeginDecode(short originalCount, long chunkSize, Pointer pData);

    public abstract short LRC_Decode(short handle, Pointer pChunk);

    public abstract void LRC_FreeHandle(short handle);

}
