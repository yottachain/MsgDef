package com.ytfs.common.codec;

import com.ytfs.common.conf.UserConfig;
import java.io.File;
import java.io.IOException;
import org.apache.commons.codec.binary.Hex;

public class TestBlockCoder {

    public static void main(String[] args) throws IOException {
        UserConfig.tmpFilePath = new File("D:");
        YTFile fragmentor = new YTFile("d:/12760eba4c0e0f4dff5e680c5b34bd5.png");
        fragmentor.init("2123142342");
        fragmentor.handle();
        
        Block b= fragmentor.getBlockList().get(0);
        b.calculate();
        
        
        System.out.println(Hex.encodeHexString(b.getKD()));

        BlockReader reader = new BlockReader("d:\\2123142342", "d:\\aa.iso");
        reader.read();
    }

}
