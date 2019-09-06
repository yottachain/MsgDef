package com.ytfs.common.eos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ytfs.common.Function;
import static com.ytfs.common.ServiceErrorCode.INVALID_SESSION;
import com.ytfs.common.ServiceException;
import com.ytfs.common.conf.ServerConfig;
import io.jafka.jeos.EosApi;
import io.jafka.jeos.EosApiFactory;
import io.jafka.jeos.LocalApi;
import io.jafka.jeos.convert.Packer;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.common.transaction.PackedTransaction;
import io.jafka.jeos.core.common.transaction.TransactionAction;
import io.jafka.jeos.core.common.transaction.TransactionAuthorization;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.response.chain.transaction.PushedTransaction;
import io.jafka.jeos.util.KeyUtil;
import io.jafka.jeos.util.Raw;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.bson.types.ObjectId;

public class EOSRequest {

    private static final AtomicInteger args = new AtomicInteger(1);

    private static long getRandomInteger(SignArg arg) {
        long nanos = (long)arg.getExpiredSecond() * 1000L * 1000L * 1000L;
        int ii = args.incrementAndGet();
        if (ii < 0) {
            return Math.abs(ii) + nanos;
        } else {
            return ii + nanos;
        }
    }

    private static byte[] encodeSignArg(SignArg req) throws JsonProcessingException {
        LocalApi localApi = EosApiFactory.createLocalApi();
        return localApi.getObjectMapper().writeValueAsBytes(req);
    }

    private static SignArg decodeSignArg(byte[] bs) throws IOException {
        LocalApi localApi = EosApiFactory.createLocalApi();
        return localApi.getObjectMapper().readValue(bs, SignArg.class);
    }

    private static byte[] encodeRequest(PushTransactionRequest req) throws JsonProcessingException {
        LocalApi localApi = EosApiFactory.createLocalApi();
        return localApi.getObjectMapper().writeValueAsBytes(req);
    }

    private static PushTransactionRequest decodeRequest(byte[] bs) throws IOException {
        LocalApi localApi = EosApiFactory.createLocalApi();
        return localApi.getObjectMapper().readValue(bs, PushTransactionRequest.class);
    }
    public static byte[] createEosClient(ObjectId id) throws JsonProcessingException {
        BpList.EOSURI uri = BpList.getEOSURI();
        EosApi eosApi = EosApiFactory.create(uri.url);
        SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
        EOSClientCache.putClient(id, eosApi);
        return encodeSignArg(arg);
    }

    public static byte[] createEosClient(String pubkey) throws JsonProcessingException {
        BpList.EOSURI uri = BpList.getEOSURI();
        EosApi eosApi = EosApiFactory.create(uri.url);
        SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
        EOSClientCache.putClient(pubkey, eosApi);
        return encodeSignArg(arg);
    }

    public static PushedTransaction request(byte[] reqdata, ObjectId id) throws IOException, ServiceException {
        EosApi eosApi = EOSClientCache.getClient(id);
        if (eosApi == null) {
            throw new ServiceException(INVALID_SESSION);
        }
        PushTransactionRequest req = decodeRequest(reqdata);
        return eosApi.pushTransaction(req);
    }

    public static PushedTransaction request(byte[] reqdata, String pubkey) throws IOException {
        EosApi eosApi = EOSClientCache.getClient(pubkey);
        PushTransactionRequest req = decodeRequest(reqdata);
        return eosApi.pushTransaction(req);
    }

    public static byte[] makeGetBalanceRequest(byte[] signarg, String username, String privateKey, String contractAccount) throws JsonProcessingException, IOException {
        SignArg arg = decodeSignArg(signarg);
        Raw raw = new Raw();
        raw.packName(username);
        raw.packUint8(1);
        raw.packName(username);
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(username, "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction(contractAccount, "getbalance", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusNanos(getRandomInteger(arg)));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String hash = sign(privateKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return encodeRequest(req);
    }

    public static byte[] makeSubBalanceRequest(byte[] signarg, String from, String privateKey, String contractAccount, long cost, int id) throws JsonProcessingException, IOException {
        SignArg arg = decodeSignArg(signarg);
        Raw raw = new Raw();
        raw.packName(from);
        raw.packUint64(cost);
        raw.packUint64(Function.inttolong(id));
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(from, "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction(contractAccount, "subbalance", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusNanos(getRandomInteger(arg)));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String hash = sign(privateKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return encodeRequest(req);
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
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.BPAccount, "active"));
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
        String hash = sign(ServerConfig.BPPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }

    public static PushTransactionRequest makeAddUsedSpaceRequest(SignArg arg, long length, String username, int id) throws JsonProcessingException, IOException {
        Raw raw = new Raw();
        raw.packName(username);
        raw.packUint64(length);
        raw.packName(ServerConfig.BPAccount);
        raw.packUint64(Function.inttolong(id));
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.BPAccount, "active"));
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
        String hash = sign(ServerConfig.BPPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }

    public static PushTransactionRequest makeSetHfeeRequest(SignArg arg, long cost, String username, int id) throws JsonProcessingException, IOException {
        Raw raw = new Raw();
        raw.packName(username);
        raw.packUint64(cost);
        raw.packName(ServerConfig.BPAccount);
        raw.packUint64(Function.inttolong(id));
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(ServerConfig.BPAccount, "active"));
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
        String hash = sign(ServerConfig.BPPriKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        return req;
    }
}
