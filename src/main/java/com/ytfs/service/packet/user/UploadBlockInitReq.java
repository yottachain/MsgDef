package com.ytfs.service.packet.user;

import org.bson.types.ObjectId;

public class UploadBlockInitReq {

    private byte[] VHP;//该数据块的明文SHA256摘要
    private ObjectId VNU; //upload id
    private short id;

    public UploadBlockInitReq() {
    }

    public UploadBlockInitReq(ObjectId VNU, byte[] VHP, short id) {
        this.VNU = VNU;
        this.VHP = VHP;
        this.id = id;
    }

    /**
     * @return the VHP
     */
    public byte[] getVHP() {
        return VHP;
    }

    /**
     * @param VHP the VHP to set
     */
    public final void setVHP(byte[] VHP) {
        this.VHP = VHP;
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
    public final void setVNU(ObjectId VNU) {
        this.VNU = VNU;
    }

    /**
     * @return the id
     */
    public short getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(short id) {
        this.id = id;
    }
}
