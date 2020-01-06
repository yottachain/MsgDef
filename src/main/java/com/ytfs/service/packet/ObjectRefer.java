package com.ytfs.service.packet;

import static com.ytfs.common.Function.bytes2Integer;
import static com.ytfs.common.Function.long2bytes;
import java.util.ArrayList;
import java.util.List;

public class ObjectRefer {

    public static List<ObjectRefer> parse(byte[][] blks) {
        List<ObjectRefer> ls = new ArrayList();
        for (byte[] bs : blks) {
            ObjectRefer refer = new ObjectRefer(bs);
            ls.add(refer);
        }
        return ls;
    }

    /**
     * 合并
     *
     * @param ls
     * @return
     */
    public static byte[][] mergeList(List<ObjectRefer> ls) {
        byte[][] blks = new byte[ls.size()][];
        int index = 0;
        for (ObjectRefer refer : ls) {
            blks[index++] = refer.toBytes();
        }
        return blks;
    }

    private long VBI;  //8
    private byte superID;  //1
    private long originalSize;  //编码前长度  6字节
    private int realSize;  //实际长度    3字节   
    private byte[] KEU;  //32
    private int keyNumber;
    private short id;//52

    public ObjectRefer() {
    }

    public ObjectRefer(byte[] bs) {
        this.VBI = bytes2Integer(bs, 0, 8);
        this.superID = bs[8];
        this.originalSize = bytes2Integer(bs, 9, 6);
        this.realSize = (int) bytes2Integer(bs, 15, 3);
        byte[] bbs = new byte[32];
        System.arraycopy(bs, 18, bbs, 0, 32);
        this.KEU = bbs;
        this.id = (short) bytes2Integer(bs, 50, 2);
        this.keyNumber = bs[52] & 0xFF;
    }

    public byte[] toBytes() {
        byte[] bs = new byte[53];
        int pos = 0;
        long2bytes(this.getVBI(), bs, pos);
        pos = pos + 8;
        bs[pos++] = this.getSuperID();
        bs[pos++] = (byte) (this.getOriginalSize() >>> 40);
        bs[pos++] = (byte) (this.getOriginalSize() >>> 32);
        bs[pos++] = (byte) (this.getOriginalSize() >>> 24);
        bs[pos++] = (byte) (this.getOriginalSize() >>> 16);
        bs[pos++] = (byte) (this.getOriginalSize() >>> 8);
        bs[pos++] = (byte) (this.getOriginalSize());

        bs[pos++] = (byte) (this.getRealSize() >>> 16);
        bs[pos++] = (byte) (this.getRealSize() >>> 8);
        bs[pos++] = (byte) (this.getRealSize());
        System.arraycopy(this.KEU, 0, bs, pos, 32);
        pos = pos + 32;
        bs[pos++] = (byte) (this.getId() >>> 8);
        bs[pos++] = (byte) (this.getId());
        bs[pos++] = (byte) (this.getKeyNumber());
        return bs;
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

    /**
     * @return the superID
     */
    public byte getSuperID() {
        return superID;
    }

    /**
     * @param superID the superID to set
     */
    public void setSuperID(byte superID) {
        this.superID = superID;
    }

    /**
     * @return the originalSize
     */
    public long getOriginalSize() {
        return originalSize;
    }

    /**
     * @param originalSize the originalSize to set
     */
    public void setOriginalSize(long originalSize) {
        this.originalSize = originalSize;
    }

    /**
     * @return the realSize
     */
    public int getRealSize() {
        return realSize;
    }

    /**
     * @param realSize the realSize to set
     */
    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }

    /**
     * @return the KEU
     */
    public byte[] getKEU() {
        return KEU;
    }

    /**
     * @param KEU the KEU to set
     */
    public void setKEU(byte[] KEU) {
        this.KEU = KEU;
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
    public void setId(short id) {
        this.id = id;
    }

    /**
     * @return the keyNumber
     */
    public int getKeyNumber() {
        return keyNumber;
    }

    /**
     * @param keyNumber the keyNumber to set
     */
    public void setKeyNumber(int keyNumber) {
        this.keyNumber = keyNumber;
    }
}
