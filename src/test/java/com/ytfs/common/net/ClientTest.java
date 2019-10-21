package com.ytfs.common.net;

import com.ytfs.common.SerializationUtil;
import com.ytfs.service.packet.UploadShardReq;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import java.io.IOException;
import org.apache.commons.codec.binary.Hex;

public class ClientTest {

    public static void main(String[] args) throws P2pHostException, IOException, InterruptedException {
        YottaP2P.start(5555, "5KQKydL7TuRwjzaFSK4ezH9RUXWuYHW1yYDp5CmQfsfTuu9MBLZ");
        String[] serverAddrs = {"/ip4/127.0.0.1/tcp/8888"};
        YottaP2P.connect("16Uiu2HAm44FX3YuzGXJgHMqnyMM5zCzeT6PUoBNZkz66LutfRREM", serverAddrs);

        for (int ii = 0; ii < 1; ii++) {
            Thread t = new Thread(new Test());
            t.start();
        }
        Thread.sleep(5000);
        //YottaP2P.disconnect("16Uiu2HAm44FX3YuzGXJgHMqnyMM5zCzeT6PUoBNZkz66LutfRREM");
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
        byte[] result1 = SerializationUtil.serialize(req);
        String ss1 = Hex.encodeHexString(result1);

        byte[] ret = YottaP2P.sendToBPUMsg("16Uiu2HAm44FX3YuzGXJgHMqnyMM5zCzeT6PUoBNZkz66LutfRREM", result1);
        System.out.println(ss1);
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
