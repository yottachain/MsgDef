package com.ytfs.service.packet;

import com.ytfs.common.SerializationUtil;
import com.ytfs.service.packet.s3.ListObjectResp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TestMap {

    public static void main(String[] args) {
        ListObjectResp ss = new ListObjectResp();

        Map<String, byte[]> map = new LinkedHashMap();
        map.put("sd", "value".getBytes());
        map.put("2323", "valque".getBytes());
        map.put("1sw343dq", "valque".getBytes());
        map.put("2sdq", "valque".getBytes());
        map.put("02323", "valque".getBytes());
        map.put("72323", "valque".getBytes());
        map.put("2sddq", "valque".getBytes());

        Set<String> sset = map.keySet();
        for (String s : sset) {
            System.out.println(s);
        }
        System.out.println("*********************************");

        Set<Map.Entry<String, byte[]>> set = map.entrySet();
        for (Map.Entry<String, byte[]> ent : set) {
            System.out.println(ent.getKey());
        }

        
        ss.setMap(map);
        byte[] bs = SerializationUtil.serialize(ss);
        ListObjectResp sss = (ListObjectResp) SerializationUtil.deserialize(bs);
        Map<String, byte[]> map1 = sss.getMap();
        Set<Map.Entry<String, byte[]>> set1 = map1.entrySet();
        for (Map.Entry<String, byte[]> ent : set1) {
            System.out.println(ent.getKey());
        }

    }
}
