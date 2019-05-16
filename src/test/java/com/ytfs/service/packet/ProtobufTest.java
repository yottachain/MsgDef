package com.ytfs.service.packet;

import com.ytfs.common.SerializationUtil;
import com.ytfs.common.ServiceException;
import java.io.IOException;

public class ProtobufTest {

    public static void main(String[] args) throws IOException {

        ServiceException err = new ServiceException(777);
        byte[] bs = SerializationUtil.serialize(err);
        System.out.println(bs.length);

        Object obj = SerializationUtil.deserialize(bs);
        if (obj instanceof ServiceException) {
            ServiceException err1 = (ServiceException) obj;
            System.out.println(err1.getErrorCode());
            System.out.println(err1.getMessage());
        }
    }
}
