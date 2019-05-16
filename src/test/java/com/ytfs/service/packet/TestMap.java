package com.ytfs.service.packet;

import com.ytfs.common.SerializationUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap();
        map.put("sd", "value");
        map.put("sdq", "valque");
        byte[] bs = SerializationUtil.serializeMap(map);

        Map<String, String> map1 = SerializationUtil.deserializeMap(bs);
        
        System.out.print(map1.size());

    }
}
