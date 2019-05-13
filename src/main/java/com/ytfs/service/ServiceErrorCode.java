package com.ytfs.service;

public class ServiceErrorCode {

    public static final int SERVER_ERROR = 0x00;//服务区内部错误
    public static final int INVALID_USER_ID = 0x01;//用户ID不存在
    public static final int NOT_ENOUGH_DHH = 0x02;//HDD空间不足
    public static final int INTERNAL_ERROR = 0x03;//网络故障
    public static final int INVALID_UPLOAD_ID = 0x04;//对话失效
    public static final int TOO_MANY_SHARDS = 0x05;//分片太多
    public static final int ILLEGAL_VHP_NODEID = 0x06;//数据块所属节点不一致
    public static final int NO_SUCH_BLOCK = 0x07;//找不到数据块元数据
    public static final int INVALID_VHB = 0x08;
    public static final int INVALID_VHP = 0x09;
    public static final int INVALID_KED = 0x0a;
    public static final int INVALID_KEU = 0x0b;
    public static final int INVALID_VHW = 0x0c;
    public static final int TOO_BIG_BLOCK = 0x0d;
    public static final int INVALID_SIGNATURE = 0x0e;
    public static final int INVALID_NODE_ID = 0x0f;
    public static final int INVALID_SHARD = 0x10;
    public static final int INVALID_BUCKET_NAME = 0x11;//用户bucket不存在
    public static final int INVALID_OBJECT_NAME = 0x12;//用户file不存在
    public static final int TOO_MANY_BUCKETS = 0x13;
    public static final int BUCKET_ALREADY_EXISTS = 0x14;
    public static final int OBJECT_ALREADY_EXISTS = 0x15;
    public static final int NODE_EXISTS = 0x16;

}
