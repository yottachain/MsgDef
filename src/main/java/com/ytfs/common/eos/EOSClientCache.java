package com.ytfs.common.eos;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.jafka.jeos.EosApi;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.bson.types.ObjectId;

public class EOSClientCache {

    private static final long MAX_SIZE = 100000;
    public static final long EXPIRED_TIME = 180;

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

    private static final Cache<ObjectId, EosApi> clients = CacheBuilder.newBuilder()
            .expireAfterWrite(EXPIRED_TIME, TimeUnit.SECONDS)
            .maximumSize(MAX_SIZE)
            .build();

    public static void putClient(ObjectId key, EosApi value) {
        clients.put(key, value);
    }

    public static EosApi getClient(ObjectId key) {
        EosApi api = clients.getIfPresent(key);
        if (api != null) {
            clients.invalidate(key);
        }
        return api;
    }

    private static final Cache<String, EosApi> clients_Reg = CacheBuilder.newBuilder()
            .expireAfterWrite(EXPIRED_TIME, TimeUnit.SECONDS)
            .maximumSize(MAX_SIZE)
            .build();

    public static void putClient(String key, EosApi value) {
        clients_Reg.put(key, value);
    }

    public static EosApi getClient(String key) {
        EosApi api = clients_Reg.getIfPresent(key);
        if (api != null) {
            clients_Reg.invalidate(key);
        }
        return api;
    }

}
