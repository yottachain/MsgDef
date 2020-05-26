package com.ytfs.common.conf;

public class ServerConfig {

    //*************************不可配置参数*************************************
    //每个文件在上传后最短天数
    public final static long PMS = 90L;

    //计费周期如:1天
    public final static long PPC = 1L;

    public static long unitCycleCost = 100000000L * PPC / 365L;
    public static long unitFirstCost = 100000000L * PMS / 365L;
    public static long unitSpace = 1024L * 1024L * 1024L;

    //周期费用统计间隔
    public static long CostSumCycle = PPC * 7L * 1000L * 60L * 60L * 24L;

    //元数据空间
    public final static long PCM = 16 * 1024;

    //小于PL2的数据块，直接记录在元数据库中
    public final static int PL2 = 256;

    //存储节点验签失败,拒绝存储,超过3次,惩罚
    public final static int PNF = 3;

    public final static int PFL = 1024 * 16;  //PFL

    //**************************可配置参数********************************
    //服务端超级节点编号,本服务节点编号
    public static int superNodeID;
    public static int isBackup = 0;
    public static String selfIp = "";

    //去重分配系数
    public static int space_factor = 100;
    //超级节点私钥
    public static String privateKey;
    public static byte[] SNDSP;

    //端口
    public static int port = 9999;

    //eos ADD
    public static String eosURI;
    public static String BPAccount;
    public static String ShadowAccount;
    public static String ShadowPriKey;
    public static String contractAccount;
    public static String contractOwnerD;

    //端口
    public static int httpPort = 8080;
    public static String httpRemoteIp = "";
    public static String httpServlet = "";

    public static int rebuildSpeed = 1000;
    public static int rebuildTaskSize = 100;
    public static int sendShardInterval = 1000;
    public static int shardNumPerNode = 10;
    
    public static int lsCacheExpireTime = 30;
    public static int lsCachePageNum = 10;
    public static int lsCursorLimit = 1;
    public static int lsCacheMaxSize = 20000;
    
    public static String s3Version = null;
}
