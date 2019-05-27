package com.ytfs.service.packet.s3;

import com.ytfs.common.SerializationUtil;
import org.bson.types.ObjectId;

import java.util.Map;

public class ListObjectResp {
    private ObjectId objectId;

    private SerializationUtil.MapObject<byte[]> mapObject;


    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

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
