package com.ytfs.common.net;

import com.ytfs.common.Function;
import com.ytfs.common.MessageFactory;
import com.ytfs.common.SerializationUtil;
import com.ytfs.common.ServiceException;
import com.ytfs.service.packet.DownloadShardReq;
import com.ytfs.service.packet.DownloadShardResp;
import com.ytfs.service.packet.UploadShardReq;
import com.ytfs.service.packet.node.GetNodeCapacityReq;
import com.ytfs.service.packet.node.GetNodeCapacityResp;
import io.jafka.jeos.util.Base58;
import io.yottachain.nodemgmt.core.vo.Node;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class ClientTest {

    public static void main(String[] args) throws P2pHostException, IOException, InterruptedException, ServiceException, NoSuchAlgorithmException {
        String VHF = "6uCp6wH17Lv8agLDDwqkgp";
        System.out.println(VHF);
        byte[] md5 = Base58.decode(VHF);
        YottaP2P.start(5555, "5KQKydL7TuRwjzaFSK4ezH9RUXWuYHW1yYDp5CmQfsfTuu9MBLZ");

        ///ip4/49.233.91.148/tcp/9001/p2p/16Uiu2HAmVcPnpzde3TKCcJkDLKkHYqxfAW99ao5u372BR2Tusioi
        String[] serverAddrs = {"/ip4/49.233.90.7/tcp/9001"};
        YottaP2P.connect("16Uiu2HAmLDz6ErTiBCJzsycZdBexhWXTftLitFWzeWmSQ2Bt7Fhs", serverAddrs);

        DownloadShardReq req = new DownloadShardReq();
        req.setVHF(md5);

        short id = MessageFactory.getMessageID(req.getClass());

        byte[] msgType = new byte[2];
        Function.short2bytes(id, msgType, 0);
        byte[] data = SerializationUtil.serializeNoID(req);
        byte[] data1 = YottaP2P.sendMsg("16Uiu2HAmLDz6ErTiBCJzsycZdBexhWXTftLitFWzeWmSQ2Bt7Fhs", msgType, data);

        DownloadShardResp resp = (DownloadShardResp) SerializationUtil.deserialize(data1);
        MessageDigest md51 = MessageDigest.getInstance("MD5");
        byte[] bs = md51.digest(resp.getData());
        System.out.println(Base58.encode(bs));

        System.in.read();
        // YottaP2P.disconnect("16Uiu2HAm44FX3YuzGXJgHMqnyMM5zCzeT6PUoBNZkz66LutfRREM");
    }

    private static void send() throws P2pHostException {
        UploadShardReq req = new UploadShardReq();
        req.setSHARDID(5);
        req.setBPDID(1);
        req.setBPDSIGN("aa".getBytes());
        req.setDAT("bb".getBytes());
        req.setUSERSIGN("cc".getBytes());
        req.setVBI(2);
        req.setVHF("dd".getBytes());
        byte[] result1 = SerializationUtil.serializeNoID(req);
        String ss1 = Hex.encodeHexString(result1);
        short id = MessageFactory.getMessageID(req.getClass());
        // byte[] ret = YottaP2P.sendMsg("16Uiu2HAm44FX3YuzGXJgHMqnyMM5zCzeT6PUoBNZkz66LutfRREM", id, result1);
        // System.out.println(ss1);
        //Object obj = SerializationUtil.deserialize(ret);
        //if (obj instanceof UploadShardResp) {
        //    UploadShardResp resp = (UploadShardResp) obj;
        //    System.out.println(resp.getRES());
        //} else {
        //    ServiceException se = (ServiceException) obj;
        //    System.out.println(se.getErrorCode());
        // }
    }

    private static class Test implements Runnable {

        @Override
        public void run() {
            for (int ii = 0; ii < 10000; ii++) {
                try {
                    send();
                } catch (Exception r) {
                    r.printStackTrace();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }

        }

    }
}
