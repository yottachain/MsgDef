package com.ytfs.common.codec;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.log4j.Logger;

public class ReadPrivateKey {
    
    private static final Logger log = Logger.getLogger(ReadPrivateKey.class);
    
    public static void main(String[] args) throws FileNotFoundException {       
        setPrivateKey("d:/cert","1234656","5JXZV8PBL5Zw87MG7GaBc6jVwzGxPQa7ZfwevN6dqLFpRNPhELw");
    }
    
    public static void setPrivateKey(String path, String password, String privatekey) throws FileNotFoundException {
        File f = new File(path);        
        OutputStream dataout = null;        
        InputStream jsin = null;
        try {
            jsin = ReadPrivateKey.class.getResourceAsStream("sjcl.js");
            dataout = new FileOutputStream(f);
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine nashorn = manager.getEngineByName("nashorn");
            InputStreamReader reader = new InputStreamReader(jsin);
            nashorn.eval(reader);            
            Object eval = nashorn.eval("sjcl.encrypt('" + password + "','" + privatekey + "',{mode:'gcm'})");
            dataout.write(eval.toString().getBytes());
        } catch (Throwable r) {
            log.error("Certificate saving failed.", r);
        } finally {
            if (jsin != null) {
                try {
                    jsin.close();
                } catch (IOException ex) {
                }
            }
            if (dataout != null) {
                try {
                    dataout.close();
                } catch (IOException ex) {
                }
            }
        }
    }
    
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
