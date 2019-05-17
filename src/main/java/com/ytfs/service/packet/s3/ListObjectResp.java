package com.ytfs.service.packet.s3;

import com.ytfs.common.SerializationUtil;

import java.util.Map;

public class ListObjectResp {
    private SerializationUtil.MapObject<byte[]> mapObject;

    public SerializationUtil.MapObject<byte[]> getMapObject() {
        return mapObject;
    }

    public void setMapObject(SerializationUtil.MapObject<byte[]> mapObject) {
        this.mapObject = mapObject;
    }


    public void setMap(Map<String,byte[]> map) {
        this.mapObject =new SerializationUtil.MapObject(map);
    }

    public Map<String,byte[]> getMap(){
        return mapObject.toMap();
    }
}
