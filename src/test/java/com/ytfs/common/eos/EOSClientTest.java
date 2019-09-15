package com.ytfs.common.eos;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class EOSClientTest {

    public static void main(String[] args) throws JsonProcessingException {
        EosApi eosApi = EosApiFactory.create("http://152.136.11.202:8888/");
        SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
        Raw raw = new Raw();
        raw.packName("username1234");
        //raw.packUint64(122);
        String transferData = raw.toHex();
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization("username1234", "active"));
        List<TransactionAction> actions = Arrays.asList(
                new TransactionAction("hddpool12345", "getbalance", authorizations, transferData)
        );
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusSeconds(arg.getExpiredSecond()));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        //5JcDH48njDbUQLu1R8SWwKsfWLnqBpWXDDiCgxFC3hioDuwLhVx
        //5JRHqh6CsoWELxCviKbkrWgkk61hQV7jEGsBAxis5NkBaRyyH8n
        String hash = sign("5JcDH48njDbUQLu1R8SWwKsfWLnqBpWXDDiCgxFC3hioDuwLhVx", arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        PushedTransaction res=eosApi.pushTransaction(req);
              LocalApi localApi = EosApiFactory.createLocalApi();
        String ss=localApi.getObjectMapper().writeValueAsString(res);
        System.out.println(ss);
    }

    private static String sign(String privateKey, SignArg arg, PackedTransaction t) {
        Raw raw = Packer.packPackedTransaction(arg.getChainId(), t);
        raw.pack(ByteBuffer.allocate(33).array());
        String hash = KeyUtil.signHash(privateKey, raw.bytes());
        return hash;
    }
}
