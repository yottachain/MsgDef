package com.ytfs.service.packet.s3.entities;

import com.ytfs.common.SerializationUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bson.types.ObjectId;

public class FileMetaData {

    private static final String LengthKey = "contentLength";
    private static final String ETagKey = "ETag";
    private static final String DateKey = "x-amz-date";

    public static byte[] toFileMetaBytes(Map<String, String> map) {
        FileMetaData fm = new FileMetaData(map);
        return fm.toBytes();
    }

    public static Map<String, String> toFileMetaMap(byte[] bs, ObjectId versionid) {
        RuntimeException err = null;
        if (bs.length > 24) {
            try {
                Map<String, String> map = SerializationUtil.deserializeMap(bs);
                String s = map.get(ETagKey);
                if (s != null && (!s.isEmpty())) {
                    return map;
                }
            } catch (RuntimeException e) {
                err = e;
            }
        }
        try {
            FileMetaData fm = new FileMetaData();
            SerializationUtil.deserializeNoID(bs, fm);
            if (fm.md5 != null) {
                Map<String, String> map = new HashMap();
                String s = Hex.encodeHexString(fm.md5);
                s = "\"" + s + "\"";
                map.put(ETagKey, s);
                map.put(LengthKey, String.valueOf(fm.length));
                if (versionid != null) {
                    map.put(DateKey, String.valueOf(versionid.getDate().getTime()));
                }
                return map;
            }
        } catch (Throwable e) {
        }
        if (err != null) {
            throw err;
        }
        return SerializationUtil.deserializeMap(bs);
    }

    private long length;
    private byte[] md5;

    public FileMetaData() {
    }

    public FileMetaData(Map<String, String> map) {
        this.length = 0;
        String s = map.get(LengthKey);
        if (s != null) {
            try {
                this.length = Long.parseLong(s);
            } catch (NumberFormatException r) {
            }
        }
        this.md5 = null;
        s = map.get(ETagKey);
        if (s != null) {
            s = s.replace("\"", "");
            try {
                this.md5 = Hex.decodeHex(s.toCharArray());
            } catch (DecoderException ex) {
            }
        }
    }

    public byte[] toBytes() {
        return SerializationUtil.serializeNoID(this);
    }

    /**
     * @return the length
     */
    public long getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * @return the md5
     */
    public byte[] getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(byte[] md5) {
        this.md5 = md5;
    }

}
