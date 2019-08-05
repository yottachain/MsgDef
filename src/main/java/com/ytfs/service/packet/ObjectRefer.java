package com.ytfs.service.packet;

import static com.ytfs.common.Function.bytes2Integer;
import static com.ytfs.common.Function.long2bytes;
import java.util.ArrayList;
import java.util.List;

public class ObjectRefer {

    public static List<ObjectRefer> parse(byte[] bs, byte[][] blks) {
        if (blks != null) {
            return parse(blks);
        } else {
            return parse(bs);
        }
    }

    private static List<ObjectRefer> parse(byte[] bs) {
        List<ObjectRefer> ls = new ArrayList();
        int len = bs.length / 52;
        for (int ii = 0; ii < len; ii++) {
            byte[] blkbs = new byte[52];
            System.arraycopy(bs, ii * 52, blkbs, 0, 52);
            ObjectRefer refer = new ObjectRefer(blkbs);
            ls.add(refer);
        }
        return ls;
    }

    private static List<ObjectRefer> parse(byte[][] blks) {
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

    /**
     * 合并
     *
     * @param ls
     * @return
     */
    public static byte[] mergeBytes(List<ObjectRefer> ls) {
        byte[] bs = new byte[52 * ls.size()];
        int pos = 0;
        for (ObjectRefer refer : ls) {
            byte[] blkbs = refer.toBytes();
            System.arraycopy(blkbs, 0, bs, pos, 52);
            pos = pos + 52;
        }
        return bs;
    }

    private long VBI;  //8
    private byte superID;  //1
    private long originalSize;  //编码前长度  6字节
    private int realSize;  //实际长度    3字节   
    private byte[] KEU;  //32
    private short id;

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
    }

    public byte[] toBytes() {
        byte[] bs = new byte[52];
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
}
