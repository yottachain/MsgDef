package com.ytfs.service.packet.s3;

import com.ytfs.service.packet.s3.entities.FileMetaMsg;

import java.util.ArrayList;
import java.util.List;

public class ListObjectResp {


    private List<FileMetaMsg> fileMetaMsgList = new ArrayList<>();

    public List<FileMetaMsg> getFileMetaMsgList() {
        return fileMetaMsgList;
    }

    public void setFileMetaMsgList(List<FileMetaMsg> fileMetaMsgList) {
        this.fileMetaMsgList = fileMetaMsgList;
    }
}
