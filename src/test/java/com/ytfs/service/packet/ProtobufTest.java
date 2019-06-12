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
        builder.addDataHash(ByteString.copyFrom("/ip4/150.109.236.221/tcp/999", "utf-8"));
        builder.addDataHash(ByteString.copyFrom("16Uiu2HAkyHhwuzkR6fRhKbhUBVMySBKKtLCRkReYTJQEy", "utf-8"));
        builder.addDataHash(ByteString.copyFrom("cccc", "utf-8"));
        builder.addParityHash(ByteString.copyFrom("eeee", "utf-8"));
        builder.addParityHash(ByteString.copyFrom("ffff", "utf-8"));
        builder.addParityHash(ByteString.copyFrom("gggg", "utf-8"));
        MessageRebuild.TaskDescription.P2PLocation.Builder b = MessageRebuild.TaskDescription.P2PLocation.newBuilder();
        b.addAddrs("1111111");
        b.addAddrs("public Timestamp readFrom(Input input)");
        b.setNodeId("ssssssssssssss");
        builder.addLocations(b.build());
        MessageRebuild.TaskDescription.P2PLocation.Builder c = MessageRebuild.TaskDescription.P2PLocation.newBuilder();
        c.addAddrs("22222222");
        c.addAddrs("public Timestamp readFrom(Input input)");
        c.setNodeId("        output.writeFixed64(number, value.getTime(), repeated);");
        builder.addLocations(c.build());
        
        builder.addRecoverId(555555);
        builder.addRecoverId(666666);
        builder.setType(1);
        byte[] result = builder.build().toByteArray();
        String ss = Hex.encodeHexString(result);
        System.out.println(ss);
        
        MessageRebuild.TaskDescription task1 = MessageRebuild.TaskDescription.parseFrom(result);
        System.out.println(task1);
        
        TaskDescription req = new TaskDescription();
        req.setId(5);
        req.setDataHash(new ArrayList());
        req.getDataHash().add("/ip4/150.109.236.221/tcp/999".getBytes("utf-8"));
        req.getDataHash().add("16Uiu2HAkyHhwuzkR6fRhKbhUBVMySBKKtLCRkReYTJQEy".getBytes("utf-8"));
        req.getDataHash().add("cccc".getBytes("utf-8"));
        req.setParityHash(new ArrayList());
        req.getParityHash().add("eeee".getBytes("utf-8"));
        req.getParityHash().add("ffff".getBytes("utf-8"));
        req.getParityHash().add("gggg".getBytes("utf-8"));
        
        TaskDescription.P2PLocation loa = new TaskDescription.P2PLocation();
        loa.setAddrs(new ArrayList());
        loa.getAddrs().add("1111111");
        loa.getAddrs().add("public Timestamp readFrom(Input input)");
        loa.setNodeId("ssssssssssssss");
        TaskDescription.P2PLocation loaa = new TaskDescription.P2PLocation();
        loaa.setAddrs(new ArrayList());
        loaa.getAddrs().add("22222222");
        loaa.getAddrs().add("public Timestamp readFrom(Input input)");
        loaa.setNodeId("        output.writeFixed64(number, value.getTime(), repeated);");
        req.setLocations(new ArrayList());
        req.getLocations().add(loa);
        req.getLocations().add(loaa);
        
        req.setRecoverId(new ArrayList());
        req.getRecoverId().add(555555);
        req.getRecoverId().add(666666);
        req.setType(1);
        byte[] result1 = SerializationUtil.serialize(req, new TaskDelegate());
        
        TaskDescription req1 = (TaskDescription) SerializationUtil.deserialize(result1);
        String ss1 = Hex.encodeHexString(result1).substring(4);
        System.out.println(ss1);
        byte[] res = new byte[result1.length - 2];
        System.arraycopy(result1, 2, res, 0, res.length);
        MessageRebuild.TaskDescription task = MessageRebuild.TaskDescription.parseFrom(res);
        List<MessageRebuild.TaskDescription.P2PLocation> ls = task.getLocationsList();
        System.out.println(task);
        
    }
}
