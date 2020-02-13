package com.ytfs.common.conf;

import io.yottachain.nodemgmt.core.vo.SuperNode;
import java.io.File;

public class UserConfig {
    //*************************不可配置参数*************************************

    //对大文件分块时,内存中保留6M数据,避免写磁盘
    public final static int Max_Memory_Usage = 1024 * 1024 * 6;

    //对大文件分块时,数据块大小
    public final static int Default_Block_Size = 1024 * 1024 * 2 - 1 - 128;//PBL

    //数据块压缩时,空出8K以备END输出
    public final static int Compress_Reserve_Size = 16 * 1024;

    //数据分片大小
    public final static int Default_Shard_Size = 16 * 1024;  //PFL

    //使用LRC编码
    public final static boolean useLRCCoder = true;

    //最大分片数
    public final static short Max_Shard_Count = 128;

    //最多容许掉线分片数量　
    public final static short Default_PND = 36;

    //小于PL2的数据块，直接记录在元数据库中
    public final static int PL2 = 256;

    //重试SN次数，5分钟
    public final static int SN_RETRYTIMES = 6 * 5;

    //LRC
    public final static short LRCMAXHANDLERS = 50;

    //**************************可配置参数********************************
    //用户钱包账户名
    public static int userId;
    public static String username;
    public static int keyNumber;

    //用户私钥
    public static byte[] KUSp;
    public static byte[] AESKey;
    public static String privateKey; //用户私钥

    //用户对应的超级节点
    public static SuperNode superNode;

    //缓存路径
    public static File tmpFilePath;

    //上传线程数
    public static int UPLOADSHARDTHREAD = 1000;
    
    public static int UPLOADBLOCKTHREAD = 100;

    //上传块线程数
    public static long UPLOADFILEMAXMEMORY = 5L * 1024L * 1024L;

    //下载线程数
    public static int DOWNLOADSHARDTHREAD = 200;

    //预分配矿机数目
    public static int PNN = (Max_Shard_Count + Default_PND) * 2;

    //预分配矿机失效时长
    public static int PTR = 1000 * 60 * 2;

    //重试次数
    public static int RETRYTIMES = 500;

    public static String zipkinServer;

    public static int CONN_EXPIRED = PTR * 5;
}
