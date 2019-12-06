package com.ytfs.common.codec.lrc;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.ytfs.common.conf.UserConfig;
import java.io.IOException;
import java.util.List;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryCache {

    public static void init() throws IOException {
        short parityCount = UserConfig.Default_PND - 23;
        short ret = LRCLibaray.INSTANCE.InitialLRC(parityCount, UserConfig.LRCMAXHANDLERS);
        if (ret == 0) {
            throw new IOException("LRCLibaray init Err.");
        }
    }

    private static final int MAX_SHARD_QUEUE_SIZE = UserConfig.LRCMAXHANDLERS * UserConfig.Max_Shard_Count * 2;
    private static final AtomicInteger SHARD_QUEUE_SIZE = new AtomicInteger(0);
    private static final ConcurrentLinkedQueue<Pointer> SHARD_QUEUE = new ConcurrentLinkedQueue();
    private static final int MAX_BLOCK_QUEUE_SIZE = UserConfig.LRCMAXHANDLERS;
    private static final AtomicInteger BLOCK_QUEUE_SIZE = new AtomicInteger(0);
    private static final ConcurrentLinkedQueue<Pointer> BLOCK_QUEUE = new ConcurrentLinkedQueue();
    private static final int MAX_PARITY_QUEUE_SIZE = UserConfig.LRCMAXHANDLERS;
    private static final AtomicInteger PARITY_QUEUE_SIZE = new AtomicInteger(0);
    private static final ConcurrentLinkedQueue<Pointer> PARITY_QUEUE = new ConcurrentLinkedQueue();

    public static void freeParityMemory(Pointer p) {
        if (p == null) {
            return;
        }
        if (PARITY_QUEUE_SIZE.get() > MAX_PARITY_QUEUE_SIZE) {
            PARITY_QUEUE_SIZE.decrementAndGet();
            long peer = Pointer.nativeValue(p);
            Native.free(peer);
            Pointer.nativeValue(p, 0);
        } else {
            PARITY_QUEUE.add(p);
        }
    }

    public static Pointer getParityMemory() {
        Pointer p = PARITY_QUEUE.poll();
        if (p != null) {
            return p;
        } else {
            p = new Memory(UserConfig.Default_Shard_Size * UserConfig.Default_PND);
            PARITY_QUEUE_SIZE.incrementAndGet();
            return p;
        }
    }

    public static void freeBlockMemory(Pointer p) {
        if (p == null) {
            return;
        }
        if (BLOCK_QUEUE_SIZE.get() > MAX_BLOCK_QUEUE_SIZE) {
            BLOCK_QUEUE_SIZE.decrementAndGet();
            long peer = Pointer.nativeValue(p);
            Native.free(peer);
            Pointer.nativeValue(p, 0);
        } else {
            BLOCK_QUEUE.add(p);
        }
    }

    public static Pointer getBlockMemory() {
        Pointer p = BLOCK_QUEUE.poll();
        if (p != null) {
            return p;
        } else {
            p = new Memory(UserConfig.Default_Shard_Size * UserConfig.Max_Shard_Count);
            BLOCK_QUEUE_SIZE.incrementAndGet();
            return p;
        }
    }

    public static void freeShardMemory(List<Pointer> p) {
        if (p == null) {
            return;
        }
        p.forEach((pointer) -> {
            freeShardMemory(pointer);
        });
    }

    public static void freeShardMemory(Pointer[] p) {
        if (p == null) {
            return;
        }
        for (Pointer pointer : p) {
            freeShardMemory(pointer);
        }
    }

    public static void freeShardMemory(Pointer p) {
        if (p == null) {
            return;
        }
        if (SHARD_QUEUE_SIZE.get() > MAX_SHARD_QUEUE_SIZE) {
            SHARD_QUEUE_SIZE.decrementAndGet();
            long peer = Pointer.nativeValue(p);
            Native.free(peer);
            Pointer.nativeValue(p, 0);
        } else {
            SHARD_QUEUE.add(p);
        }
    }

    public static Pointer getShardMemory() {
        Pointer p = SHARD_QUEUE.poll();
        if (p != null) {
            return p;
        } else {
            p = new Memory(UserConfig.Default_Shard_Size);
            SHARD_QUEUE_SIZE.incrementAndGet();
            return p;
        }
    }
}
