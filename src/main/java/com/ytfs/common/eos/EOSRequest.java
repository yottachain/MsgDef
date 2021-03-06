package com.ytfs.common.eos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ytfs.common.conf.ServerConfig;
import io.jafka.jeos.convert.Packer;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.common.transaction.PackedTransaction;
import io.jafka.jeos.core.common.transaction.TransactionAction;
import io.jafka.jeos.core.common.transaction.TransactionAuthorization;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.util.KeyUtil;
import io.jafka.jeos.util.Raw;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EOSRequest {

    private static final AtomicInteger args = new AtomicInteger(1);

    private static long getRandomInteger(SignArg arg) {
        long nanos = (long) arg.getExpiredSecond() * 1000L * 1000L * 1000L;
        int ii = args.incrementAndGet();
        if (ii < 0) {
            return Math.abs(ii) + nanos;
        } else {
            return ii + nanos;
        }
    }

    //void subbalance(name user, int64_t balance, uint8_t acc_type, name caller)
    public static PushTransactionRequest makeSubBalanceRequest(SignArg arg, String from, long cost) throws JsonProcessingException, IOException {
        Raw raw = new Raw();
        raw.packName(from);
        raw.packUint64(cost);
        //raw.packUint64(Function.inttolong(id));
        raw.packUint8(2);
        raw.packName(ServerConfig.BPAccount);
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.ShadowAccount, "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction(ServerConfig.contractAccount, "subbalance", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusNanos(getRandomInteger(arg)));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String hash = sign(ServerConfig.ShadowPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }

    private static String sign(String privateKey, SignArg arg, PackedTransaction t) {
        Raw raw = Packer.packPackedTransaction(arg.getChainId(), t);
        raw.pack(ByteBuffer.allocate(33).array());
        String hash = KeyUtil.signHash(privateKey, raw.bytes());
        return hash;
    }

    public static PushTransactionRequest makeGetBalanceRequest(SignArg arg, String username) throws JsonProcessingException, IOException {
        Raw raw = new Raw();
        raw.packName(username);
        raw.packUint8(2);
        raw.packName(ServerConfig.BPAccount);
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.ShadowAccount, "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction(ServerConfig.contractAccount, "getbalance", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusNanos(getRandomInteger(arg)));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String hash = sign(ServerConfig.ShadowPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }

    //void addhspace(name user, uint64_t space, name caller);
    public static PushTransactionRequest makeAddUsedSpaceRequest(SignArg arg, long length, String username) throws JsonProcessingException, IOException {
        Raw raw = new Raw();
        raw.packName(username);
        raw.packUint64(length);
        raw.packName(ServerConfig.BPAccount);
        //raw.packUint64(Function.inttolong(id));
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.ShadowAccount, "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction(ServerConfig.contractAccount, "addhspace", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusNanos(getRandomInteger(arg)));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String hash = sign(ServerConfig.ShadowPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }

    //void subhspace(name user, uint64_t space, name caller);
    public static PushTransactionRequest makeSetHfeeRequest(SignArg arg, long cost, String username) throws JsonProcessingException, IOException {
        Raw raw = new Raw();
        raw.packName(username);
        raw.packUint64(cost);
        raw.packName(ServerConfig.BPAccount);
        //raw.packUint64(Function.inttolong(id));
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.ShadowAccount, "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction(ServerConfig.contractAccount, "sethfee", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusNanos(getRandomInteger(arg)));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String hash = sign(ServerConfig.ShadowPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }
}
