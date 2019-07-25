package com.ytfs.service.packet.s3.entities;

import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

import java.util.List;

public class FileMetaMsg {

    private ObjectId fileId;
    private ObjectId bucketId;
    private String fileName;
    private ObjectId versionId;//对应上传文件ID:VNU
    private boolean latest;
    private byte[] meta;
    private byte[] acl;

    public FileMetaMsg() {
    }

    public FileMetaMsg(ObjectId versionId, ObjectId bucketId, String fileName) {
        this.versionId = versionId;
        this.bucketId = bucketId;
        this.fileName = fileName;
    }

    public FileMetaMsg(Document doc) {
        this.setFields(doc);
        if (doc.containsKey("version")) {
            List ls = (List) doc.get("version");
            Document verdoc = (Document) ls.get(0);
            this.setVersion(verdoc);
        }
    }

    public FileMetaMsg(Document doc, Document verdoc) {
        this.setFields(doc);
        this.setVersion(verdoc);
    }

    public final void setFields(Document doc) {
        if (doc.containsKey("_id")) {
            this.fileId = doc.getObjectId("_id");
        }
        if (doc.containsKey("bucketId")) {
            this.bucketId = doc.getObjectId("bucketId");
        }
        if (doc.containsKey("fileName")) {
            this.fileName = doc.getString("fileName");
        }
    }

    public final void setVersion(Document verdoc) {
        if (verdoc.containsKey("versionId")) {
            this.versionId = verdoc.getObjectId("versionId");
        }
        if (verdoc.containsKey("meta")) {
            this.meta = ((Binary) verdoc.get("meta")).getData();
        }
        if (verdoc.containsKey("acl")) {
            this.acl = ((Binary) verdoc.get("acl")).getData();
        }
    }

    public Document getVerDocument() {
        Document doc = new Document();
        doc.append("versionId", versionId);
        doc.append("meta", meta == null ? new Binary(new byte[0]) : new Binary(meta));
        doc.append("acl", acl == null ? new Binary(new byte[0]) : new Binary(acl));
        return doc;
    }

    /**
     * @return the fileId
     */
    public ObjectId getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(ObjectId fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the bucketId
     */
    public ObjectId getBucketId() {
        return bucketId;
    }

    /**
     * @param bucketId the bucketId to set
     */
    public void setBucketId(ObjectId bucketId) {
        this.bucketId = bucketId;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the version
     */
    public ObjectId getVersionId() {
        return versionId;
    }

    /**
     * @param version the version to set
     */
    public void setVersionId(ObjectId version) {
        this.versionId = version;
    }

    /**
     * @return the meta
     */
    public byte[] getMeta() {
        return meta;
    }

    /**
     * @param meta the meta to set
     */
    public void setMeta(byte[] meta) {
        this.meta = meta;
    }

    /**
     * @return the acl
     */
    public byte[] getAcl() {
        return acl;
    }

    /**
     * @param acl the acl to set
     */
    public void setAcl(byte[] acl) {
        this.acl = acl;
    }

    public boolean isLatest() {
        return latest;
    }

    public void setLatest(boolean latest) {
        this.latest = latest;
    }
}
