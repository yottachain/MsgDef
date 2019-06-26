package com.ytfs.common.codec;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.log4j.Logger;

public class ReadPrivateKey {

    private static final Logger log = Logger.getLogger(ReadPrivateKey.class);

    public static String getPrivateKey(String path, String password) {
        File f = new File(path);
        if (!f.exists()) {
            log.error("Certificate file '" + path + "' is invalid.");
            return null;
        } else {
            if (f.length() > 1024 * 1024) {
                log.error("Certificate file '" + path + "' is  too large.");
                return null;
            }
        }
        InputStream jsin = null;
        InputStream datain = null;
        try {
            jsin = ReadPrivateKey.class.getResourceAsStream("sjcl.js");
            datain = new BufferedInputStream(new FileInputStream(f));
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine nashorn = manager.getEngineByName("nashorn");
            InputStreamReader reader = new InputStreamReader(jsin);
            nashorn.eval(reader);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int b = 0;
            while ((b = datain.read()) != -1) {
                os.write(b);
            }
            String ct = new String(os.toByteArray());
            Object eval = nashorn.eval("sjcl.decrypt('" + password + "','" + ct + "',{mode:'gcm'})");
            return eval.toString();
        } catch (Throwable r) {
            log.error("Certificate loading failed.", r);
            return null;
        } finally {
            if (jsin != null) {
                try {
                    jsin.close();
                } catch (IOException ex) {
                }
            }
            if (datain != null) {
                try {
                    datain.close();
                } catch (IOException ex) {
                }
            }
        }
    }

}
