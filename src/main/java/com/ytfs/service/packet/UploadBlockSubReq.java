package com.ytfs.service.packet;

import java.util.List;
import org.bson.types.ObjectId;

public class UploadBlockSubReq {

    private int shardCount = 0;//需要返回存储分片的节点个数
    private ObjectId VNU; //upload id
    private long VBI;
    private List<Integer> errid;

    /**
     * @return the shardCount
     */
    public int getShardCount() {
        return shardCount;
    }

    /**
     * @param shardCount the shardCount to set
     */
    public void setShardCount(int shardCount) {
        this.shardCount = shardCount;
    }

    /**
     * @return the VNU
     */
    public ObjectId getVNU() {
        return VNU;
    }

    /**
     * @param VNU the VNU to set
     */
    public void setVNU(ObjectId VNU) {
        this.VNU = VNU;
    }

    /**
     * @return the errid
     */
    public List<Integer> getErrid() {
        return errid;
    }

    /**
     * @param errid the errid to set
     */
    public void setErrid(List<Integer> errid) {
        this.errid = errid;
    }

    /**
     * @return the VBI
     */
    public long getVBI() {
        return VBI;
    }

    /**
     * @param VBI the VBI to set
     */
    public void setVBI(long VBI) {
        this.VBI = VBI;
    }

}
