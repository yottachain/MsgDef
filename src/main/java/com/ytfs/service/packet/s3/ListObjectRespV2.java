package com.ytfs.service.packet.s3;

import com.ytfs.common.SerializationUtil;
import com.ytfs.service.packet.s3.entities.FileMetaMsg;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ListObjectRespV2 {

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFileMetaMsgList(List<FileMetaMsg> fileMetaMsgList) {
        try {
            ListObjectResp resp = new ListObjectResp();
            resp.setFileMetaMsgList(fileMetaMsgList);
            byte[] bs = SerializationUtil.serializeNoID(resp);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream zip = new GZIPOutputStream(out);
            zip.write(bs);
            zip.close();
            this.data = out.toByteArray();
        } catch (IOException r) {
            throw new RuntimeException(r);
        }
    }

    public List<FileMetaMsg> getFileMetaMsgList() {
        if (data == null) {
            return new ArrayList();
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPInputStream zip = new GZIPInputStream(new ByteArrayInputStream(data));
            int len;
            byte[] buf = new byte[4096];
            while ((len = zip.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            if(out.toByteArray().length==0){
                return new ArrayList();
            }
            ListObjectResp resp = new ListObjectResp();           
            SerializationUtil.deserializeNoID(out.toByteArray(), resp);
            return resp.getFileMetaMsgList();
        } catch (IOException r) {
            throw new RuntimeException(r);
        }
    }

}
