package com.ytfs.service.packet;

import com.ytfs.common.SerializationUtil;
import com.ytfs.service.packet.s3.ListObjectResp;
import java.util.HashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
        ListObjectResp ss = new ListObjectResp();

        Map<String, byte[]> map = new HashMap();
        //map.put("sd", "value".getBytes());
        //map.put("sdq", "valque".getBytes());

        ss.setMap(map);

        byte[] bs = SerializationUtil.serialize(ss);

        ListObjectResp sss = (ListObjectResp) SerializationUtil.deserialize(bs);
        Map<String, byte[]> map1 = sss.getMap();
        System.out.print(map1.size());

    }
}
