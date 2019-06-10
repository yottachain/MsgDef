package com.ytfs.service.packet.s3;

import java.util.ArrayList;
import java.util.List;

public class ListObjectResp {

    private List<Object> objects = new ArrayList<>();


    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
