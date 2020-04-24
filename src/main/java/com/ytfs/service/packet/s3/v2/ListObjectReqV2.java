package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;
import io.jafka.jeos.util.Base58;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bson.types.ObjectId;

public class ListObjectReqV2 extends AuthReq {

    private String bucketName;

    private ObjectId startId;

    private Integer limit;

    private String fileName;

    private String prefix;

    private boolean version;

    private ObjectId nextVersionId;

    private boolean compress = false;

    public String getHashCode(int userid) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
             md5.update(String.valueOf(userid).getBytes());
            if (bucketName != null && !bucketName.isEmpty()) {
                md5.update(bucketName.getBytes(Charset.forName("UTF-8")));
            }
            if (startId != null) {
                md5.update(startId.toByteArray());
            }
            md5.update(String.valueOf(limit).getBytes());
            if (fileName != null && !fileName.isEmpty()) {
                md5.update(fileName.getBytes(Charset.forName("UTF-8")));
            }
            if (prefix != null && !prefix.isEmpty()) {
                md5.update(prefix.getBytes(Charset.forName("UTF-8")));
            }
            md5.update(version ? (byte) 1 : (byte) 0);
            if (nextVersionId != null) {
                md5.update(nextVersionId.toByteArray());
            }
            md5.update(compress ? (byte) 1 : (byte) 0);
            byte[] bs = md5.digest();
            return Base58.encode(bs);
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }

    public ObjectId getNextVersionId() {
        return nextVersionId;
    }

    public void setNextVersionId(ObjectId nextVersionId) {
        this.nextVersionId = nextVersionId;
    }

    public boolean isVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ObjectId getStartId() {
        return startId;
    }

    public void setStartId(ObjectId startId) {
        this.startId = startId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * @return the compress
     */
    public boolean isCompress() {
        return compress;
    }

    /**
     * @param compress the compress to set
     */
    public void setCompress(boolean compress) {
        this.compress = compress;
    }

}
