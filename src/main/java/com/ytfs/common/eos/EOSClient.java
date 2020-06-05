package com.ytfs.common.eos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytfs.common.conf.ServerConfig;
import com.ytfs.common.eos.BpList.EOSURI;
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

public class EOSClient {

    private static final Logger LOG = Logger.getLogger(EOSClient.class);
    private static final boolean BP_ENABLE;

    static {
        String s = System.getenv("IPFS_BP_ENABLE");
        BP_ENABLE = !(s != null && s.trim().equalsIgnoreCase("no"));
    }

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
        long needcost = ServerConfig.unitFirstCost * length / ServerConfig.unitSpace;
        return balance > needcost;
    }

    public static long getBalance(String username) throws Exception {
        if (!BP_ENABLE) {
            return Long.MAX_VALUE;
        }
        Exception err = null;
        for (int ii = 0; ii < 3; ii++) {
            EOSURI uri = BpList.getEOSURI();
            try {
                EosApi eosApi = EosApiFactory.create(uri.url);
                SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
                PushTransactionRequest req = makeGetBalanceRequest(arg, username);
                PushedTransaction pts = eosApi.pushTransaction(req);
                String console = pts.getProcessed().getActionTraces().get(0).getConsole();
                
                int index = console.indexOf("{\"balance\":");
                console = console.substring(index);
                index = console.indexOf("}");
                console = console.substring(0, index + 1);
                ObjectMapper mapper = new ObjectMapper();
                Map readValue = mapper.readValue(console, Map.class);
                Object obj = readValue.get("balance");
                return (obj instanceof Integer) ? (int) obj : (long) obj;
            } catch (Exception r) {
                err = r;
                if (!uri.setErr(r)) {
                    throw r;
                }
            }
        }
        throw err;
    }

    /**
     * 扣除初始费用
     *
     * @param firstCost
     * @param username
     * @throws Throwable
     */
    public static void deductHDD(long firstCost, String username) throws Throwable {
        if (!BP_ENABLE) {
            return;
        }
        Exception err = null;
        for (int ii = 0; ii < 8; ii++) {
            EOSURI uri = BpList.getEOSURI();
            try {
                EosApi eosApi = EosApiFactory.create(uri.url);
                SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
                PushTransactionRequest req = EOSRequest.makeSubBalanceRequest(arg, username, firstCost);
                eosApi.pushTransaction(req);
                return;
            } catch (Exception r) {
                err = r;
                if (!uri.setErr(r)) {
                    throw r;
                }
            }
        }
        throw err;
    }

    /**
     * 增加用户使用空间
     *
     * @param length
     * @param username
     * @throws Throwable
     */
    public static void addUsedSpace(long length, String username) throws Throwable {
        if (!BP_ENABLE) {
            return;
        }
        Exception err = null;
        for (int ii = 0; ii < 8; ii++) {
            EOSURI uri = BpList.getEOSURI();
            try {
                EosApi eosApi = EosApiFactory.create(uri.url);
                SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
                PushTransactionRequest req = makeAddUsedSpaceRequest(arg, length, username);
                eosApi.pushTransaction(req);
                return;
            } catch (Exception r) {
                err = r;
                if (!uri.setErr(r)) {
                    throw r;
                }
            }
        }
        throw err;
    }

    /**
     * 设置周期费用
     *
     * @param cost
     * @param username
     * @throws Throwable
     */
    public static void setUserFee(long cost, String username) throws Throwable {
        if (!BP_ENABLE) {
            return;
        }
        Exception err = null;
        for (int ii = 0; ii < 8; ii++) {
            EOSURI uri = BpList.getEOSURI();
            try {
                EosApi eosApi = EosApiFactory.create(uri.url);
                SignArg arg = eosApi.getSignArg((int) EOSClientCache.EXPIRED_TIME);
                PushTransactionRequest req = makeSetHfeeRequest(arg, cost, username);
                eosApi.pushTransaction(req);
                return;
            } catch (Exception r) {
                err = r;
                if (!uri.setErr(r)) {
                    throw r;
                }
            }
        }
        throw err;
    }

}
