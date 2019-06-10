package com.ytfs.service.packet;

import com.google.protobuf.ByteString;
import com.ytfs.common.SerializationUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Hex;

public class UploadShardRequestSerial {

    public static void main(String[] args) throws IOException {
        MessageRebuild.TaskDescription.Builder builder=MessageRebuild.TaskDescription.newBuilder();
        
        builder.setId(0);
 /*
        builder.setMaxDataSpace(1);
        builder.setNodeid("sssss");
        builder.setOwner("aaaaa");
        MessageNode.NodeRegReq info = builder.build();
        byte[] result = info.toByteArray();
        String ss = Hex.encodeHexString(result);
        System.out.println(ss);
*/
        com.ytfs.service.packet.NodeRegReq req = new com.ytfs.service.packet.NodeRegReq();
        List<String> ls = new ArrayList();
        ls.add("sddvalue1");
        ls.add("sddvalue2");
        req.setAddrs(ls);
        req.setMaxDataSpace(1);
        req.setNodeid("sssss");
        req.setOwner("aaaaa");
        byte[] result1 = SerializationUtil.serialize(req);
        String ss1 = Hex.encodeHexString(result1);
        System.out.println(ss1);

    }
}
