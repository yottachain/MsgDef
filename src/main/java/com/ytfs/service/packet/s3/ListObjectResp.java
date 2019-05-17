package com.ytfs.service.packet.s3;

import com.ytfs.common.SerializationUtil;

import java.util.Map;

public class ListObjectResp {

    Map<String,byte[]> map;
    SerializationUtil.MapObject mapObject = new SerializationUtil.MapObject(map);

    public Map<String, byte[]> getMap() {
        return map;
    }

    public void setMap(Map<String, byte[]> map) {
        this.map = map;
    }




}
