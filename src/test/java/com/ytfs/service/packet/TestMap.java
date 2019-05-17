package com.ytfs.service.packet;

import com.ytfs.common.SerializationUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {

        Map<String, byte[]> map = new HashMap();
        map.put("sd", "value".getBytes());
        map.put("sdq", "valque".getBytes());
        byte[] bs = SerializationUtil.serializeMap(map);

        Map<String, byte[]> map1 = SerializationUtil.deserializeMap(bs);
        
        System.out.print(map1.size());

    }
}
