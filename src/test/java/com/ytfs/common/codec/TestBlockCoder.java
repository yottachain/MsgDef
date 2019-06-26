package com.ytfs.common.codec;

import com.ytfs.common.conf.UserConfig;
import java.io.File;
import java.io.IOException;

public class TestBlockCoder {

    public static void main(String[] args) throws IOException {
        UserConfig.tmpFilePath = new File("D:");
        YTFile fragmentor = new YTFile("d:\\goland-2019.1.3.exe");
        fragmentor.init("2123142342");
        fragmentor.handle();

        BlockReader reader = new BlockReader("d:\\2123142342", "d:\\aa.iso");
        reader.read();
    }

}
