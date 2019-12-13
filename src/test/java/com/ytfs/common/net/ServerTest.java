package com.ytfs.common.net;

import com.ytfs.common.SerializationUtil;
import com.ytfs.common.ServiceException;
import com.ytfs.service.packet.UploadShardReq;
import io.yottachain.p2phost.YottaP2P;
import io.yottachain.p2phost.core.exception.P2pHostException;
import io.yottachain.p2phost.interfaces.Callback;
import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) throws P2pHostException, IOException {
        YottaP2P.start(8888, "5JdDoNZwSADC3KG7xCh7mCF62fKp86sLf3GUNDY2B8t2UUB9HdJ");
        YottaP2P.registerCallback(userCallback);
        System.out.println(YottaP2P.id());
        //打印监听地址列表
        String[] addrs = YottaP2P.addrs();
        for (String str : addrs) {
            System.out.println(str);
        }

        System.in.read();
    }

    static Callback userCallback = new Callback() {
        @Override
        public byte[] onMessage(byte[] bytes, String string) {
            System.out.println(string);

            Object obj = SerializationUtil.deserialize(bytes);
            if (obj instanceof UploadShardReq) {
                UploadShardReq req = (UploadShardReq) obj;
                System.out.println(req.getBPDID());

                // try { 
                // URL url=new URL("www.sohu.com");
                //  URLConnection con= url.openConnection();
                //  } catch (Exception ex) {
                // }
                // return SerializationUtil.serialize(resp);
                return null;
            } else {
                ServiceException se = new ServiceException(1000);
                return SerializationUtil.serialize(se);
            }
        }
    };

}
