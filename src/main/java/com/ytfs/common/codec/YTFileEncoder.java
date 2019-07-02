package com.ytfs.common.codec;

import com.ytfs.common.Function;
import static com.ytfs.common.conf.UserConfig.Compress_Reserve_Size;
import static com.ytfs.common.conf.UserConfig.Default_Block_Size;
import static com.ytfs.common.conf.UserConfig.Max_Memory_Usage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DeflaterOutputStream;

public class YTFileEncoder {

    private final InputStream is;
    private final byte[] buf = new byte[16];
    private long length;
    private byte[] VHW;
    private boolean finished = false;
    private Block curBlock = null;

    public YTFileEncoder(byte[] bs) throws IOException {
        if (bs.length >= Max_Memory_Usage) {
            throw new IOException("");
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(bs);
        digest(bi);
        is = new BackableBytesInputSteam(bs);
        length = bs.length;
    }

    private void digest(InputStream is) throws IOException {
        byte[] data = new byte[8192];
        int len;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            while ((len = is.read(data)) != -1) {
                sha256.update(data, 0, len);
            }
            this.VHW = sha256.digest();
        } catch (NoSuchAlgorithmException ex) {
            is.close();
            throw new IOException(ex);
        } finally {
            is.close();
        }
    }

    public YTFileEncoder(String path) throws IOException {
        File file = new File(path);
        digest(new FileInputStream(file));
        is = new BackableBufferedInputSteam(new FileInputStream(file), Default_Block_Size);
        length = file.length();
    }

    public Block handle() throws IOException {
        if (isFinished()) {
            return null;
        }
        try {
            long readTotal = deflate(is);
            if (readTotal > 0) {
                is.skip(readTotal * -1);
                pack(is);
            }
            return curBlock;
        } catch (IOException e) {
            is.close();
            throw e;
        }
    }

    /**
     * 不压缩,打包
     *
     * @param is
     * @throws IOException
     */
    private void pack(InputStream is) throws IOException {
        byte[] data = new byte[Default_Block_Size];
        int len = -1;//不压缩,head置-1
        Function.short2bytes((short) len, data, 0);
        len = is.read(data, 2, data.length - 2);
        if (len < data.length - 2) {
            byte[] newdata = new byte[2 + len];
            System.arraycopy(data, 0, newdata, 0, newdata.length);
            curBlock = new Block(newdata, len);
            finished = true;
        } else {
            curBlock = new Block(data, Default_Block_Size - 2);
        }
    }

    /**
     * 压缩
     *
     * @param is
     * @return >0 压缩失败返回totalIn(读取了多少字节,需要撤回) 0 OK
     * @throws IOException
     */
    private long deflate(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(new byte[]{0, 0});
        DeflaterOutputStream dos = new DeflaterOutputStream(bos);
        long totalIn = 0;
        int len;
        while ((len = is.read(buf)) != -1) {
            dos.write(buf, 0, len);
            totalIn = totalIn + len;
            int remainSize = Default_Block_Size - bos.size();
            if (remainSize < 0) {
                return totalIn;
            }
            if (remainSize < Compress_Reserve_Size) {
                dos.close();
                if (totalIn - bos.size() <= 0) {
                    return totalIn;
                }
                remainSize = Default_Block_Size - bos.size();
                if (remainSize < 0) {//失败
                    return totalIn;
                } else {
                    byte[] bs = new byte[remainSize];
                    len = is.read(bs);
                    if (len == -1) {
                        byte[] data = bos.toByteArray();//后续无原文字节,head置0
                        curBlock = new Block(data, totalIn);
                        finished = true;
                    } else {
                        totalIn = totalIn + len;
                        bos.write(bs, 0, len);
                        byte[] data = bos.toByteArray();//后续有原文字节,head置len
                        Function.short2bytes((short) len, data, 0);
                        curBlock = new Block(data, totalIn);
                        if (len < remainSize) {
                            finished = true;
                        }
                    }
                    return 0;
                }
            }
        }
        dos.close();
        if (totalIn - bos.size() <= 0) {
            return totalIn; //没必要压缩
        }
        if (bos.size() > Default_Block_Size) {
            return totalIn; //失败
        }
        if (totalIn > 0) {
            byte[] data = bos.toByteArray();//后续无原文字节,head置0
            curBlock = new Block(data, totalIn);
        }
        finished = true;
        return 0;
    }

    /**
     * @return the length
     */
    public long getLength() {
        return length;
    }

    /**
     * @return the VHW
     */
    public byte[] getVHW() {
        return VHW;
    }

    /**
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }
}
