package com.ytfs.common.eos;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EOSClientCache {

    private static final long MAX_SIZE = 100000;
    public static final long EXPIRED_TIME = 90;

    private static final Cache<String, Long> userBanlance = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .maximumSize(MAX_SIZE)
            .build();

    public static long getBalance(String username) throws Throwable {
        try {
            return userBanlance.get(username, () -> EOSClient.getBalance(username));
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

}
