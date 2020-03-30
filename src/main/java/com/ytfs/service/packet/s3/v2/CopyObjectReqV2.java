package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;


public class CopyObjectReqV2 extends AuthReq {

    private String srcBucket;
    private String srcObjectKey;
    private String  destBucket;
    private String destObjectKey;
    private byte[] meta;

    public byte[] getMeta() {
        return meta;
    }

    public void setMeta(byte[] meta) {
        this.meta = meta;
    }

    public String getSrcBucket() {
        return srcBucket;
    }

    public void setSrcBucket(String srcBucket) {
        this.srcBucket = srcBucket;
    }

    public String getSrcObjectKey() {
        return srcObjectKey;
    }

    public void setSrcObjectKey(String srcObjectKey) {
        this.srcObjectKey = srcObjectKey;
    }

    public String getDestBucket() {
        return destBucket;
    }

    public void setDestBucket(String destBucket) {
        this.destBucket = destBucket;
    }

    public String getDestObjectKey() {
        return destObjectKey;
    }

    public void setDestObjectKey(String destObjectKey) {
        this.destObjectKey = destObjectKey;
    }
}
