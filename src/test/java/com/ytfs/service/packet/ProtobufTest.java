package com.ytfs.service.packet;

import com.google.protobuf.ByteString;
import com.ytfs.common.SerializationUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Hex;

public class ProtobufTest {

    public static void main(String[] args) throws IOException {
        taskDescriptionTest();
    }

    public static void taskDescriptionTest() throws IOException {
        MessageRebuild.TaskDescription.Builder builder = MessageRebuild.TaskDescription.newBuilder();
        builder.setId(5);
        builder.addDataHash(ByteString.copyFrom("aaaa", "utf-8"));
        builder.addDataHash(ByteString.copyFrom("bbbb", "utf-8"));
        builder.addDataHash(ByteString.copyFrom("cccc", "utf-8"));
        builder.addParityHash(ByteString.copyFrom("eeee", "utf-8"));
        builder.addParityHash(ByteString.copyFrom("ffff", "utf-8"));
        builder.addParityHash(ByteString.copyFrom("gggg", "utf-8"));
        MessageRebuild.TaskDescription.P2PLocation.Builder b = MessageRebuild.TaskDescription.P2PLocation.newBuilder();
        b.addAddrs("1111111");
        b.addAddrs("2222222");
        b.setNodeId("ssssssssssssss");
        builder.addLocations(b.build());
        builder.addRecoverId(0);
        builder.addRecoverId(1);
        byte[] result = builder.build().toByteArray();
        String ss = Hex.encodeHexString(result);
        System.out.println(ss);

        MessageRebuild.TaskDescription task1 = MessageRebuild.TaskDescription.parseFrom(result);
        System.out.println(task1);

        TaskDescription req = new TaskDescription();
        req.setId(5);
        req.setDataHash(new ArrayList());
        req.getDataHash().add("aaaa".getBytes("utf-8"));
        req.getDataHash().add("bbbb".getBytes("utf-8"));
        req.getDataHash().add("cccc".getBytes("utf-8"));
        req.setParityHash(new ArrayList());
        req.getParityHash().add("eeee".getBytes("utf-8"));
        req.getParityHash().add("ffff".getBytes("utf-8"));
        req.getParityHash().add("gggg".getBytes("utf-8"));

        TaskDescription.P2PLocation loa = new TaskDescription.P2PLocation();
        loa.setAddrs(new ArrayList());
        loa.getAddrs().add("1111111");
        loa.getAddrs().add("2222222");
        loa.setNodeId("ssssssssssssss");
        req.setLocations(new ArrayList());
        req.getLocations().add(loa);
        req.setRecoverId(new ArrayList());
        req.getRecoverId().add(0);
        req.getRecoverId().add(1);

        byte[] result1 = SerializationUtil.serialize(req);
        
        TaskDescription req1=(TaskDescription)SerializationUtil.deserialize(result1);
        String ss1 = Hex.encodeHexString(result1).substring(4);
        System.out.println(ss1);
        byte[] res = new byte[result1.length - 2];
        System.arraycopy(result1, 2, res, 0, res.length);
        MessageRebuild.TaskDescription task = MessageRebuild.TaskDescription.parseFrom(res);
        List<MessageRebuild.TaskDescription.P2PLocation>  ls=task.getLocationsList();
        System.out.println(task);
        

    }
}
