package com.ytfs.common.eos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytfs.common.conf.ServerConfig;
import com.ytfs.common.conf.UserConfig;
import static com.ytfs.common.eos.EOSRequest.makeAddUsedSpaceRequest;
import static com.ytfs.common.eos.EOSRequest.makeGetBalanceRequest;
import static com.ytfs.common.eos.EOSRequest.makeSetHfeeRequest;
import io.jafka.jeos.EosApi;
import io.jafka.jeos.EosApiFactory;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.response.chain.transaction.PushedTransaction;
import java.util.Map;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

public class EOSClient {

    private static final Logger LOG = Logger.getLogger(EOSClient.class);

    /**
     * 该用户是否有足够的HDD用于存储该数据最短存储时间PMS（例如60天）
     *
     * @param length 数据长度
     * @param username
     * @return true：有足够空间，false：没有
     * @throws java.lang.Throwable
     */
    public static boolean hasSpace(long length, String username) throws Throwable {
        long balance = EOSClientCache.getBalance(username);
        LOG.info("Get [" + username + "] balance:" + balance);
        long unitcount = length / UserConfig.Default_Shard_Size;
        long remain = length % UserConfig.Default_Shard_Size > 0 ? 1 : 0;
        long needcost = ServerConfig.unitcost * (unitcount + remain);
        return balance > needcost;
    }

    public static long getBalance(String username) throws Throwable {
        EosApi eosApi = EosApiFactory.create(ServerConfig.eosURI);
        SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
        PushTransactionRequest req = makeGetBalanceRequest(arg, username);
        PushedTransaction pts = eosApi.pushTransaction(req);
        String console = pts.getProcessed().getActionTraces().get(0).getConsole();
        ObjectMapper mapper = new ObjectMapper();
        Map readValue = mapper.readValue(console, Map.class);
        Object obj = readValue.get("balance");
        return (obj instanceof Integer) ? (int) obj : (long) obj;
    }

    /**
     * 扣除初始费用
     *
     * @param reqdata
     * @param id
     * @throws Throwable
     */
    public static void deductHDD(byte[] reqdata, ObjectId id) throws Throwable {
        EOSRequest.request(reqdata, id);
    }

    /**
     * 增加用户使用空间
     *
     * @param length
     * @param username
     * @param id
     * @throws Throwable
     */
    public static void addUsedSpace(long length, String username, int id) throws Throwable {
        EosApi eosApi = EosApiFactory.create(ServerConfig.eosURI);
        SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
        PushTransactionRequest req = makeAddUsedSpaceRequest(arg, length, username, id);
        eosApi.pushTransaction(req);
    }

    /**
     * 设置周期费用
     *
     * @param cost
     * @param username
     * @param id
     * @throws Throwable
     */
    public static void setUserFee(long cost, String username, int id) throws Throwable {
        EosApi eosApi = EosApiFactory.create(ServerConfig.eosURI);
        SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
        PushTransactionRequest req = makeSetHfeeRequest(arg, cost, username, id);
        eosApi.pushTransaction(req);
    }

}
