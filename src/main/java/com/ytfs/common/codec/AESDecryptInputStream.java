package com.ytfs.common.codec;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESDecryptInputStream extends FilterInputStream {

    private int bufLen = 8192;
    private final byte[] buf;
    private ByteArrayInputStream bufin = null;
    private final Cipher cipher;
    private boolean reachEOF = false;

    public AESDecryptInputStream(InputStream in, byte[] key) throws IOException {
        this(in, key, 8192);
    }

    public AESDecryptInputStream(InputStream in, byte[] key, int bufLen) throws IOException {
        super(in);
        this.bufLen = bufLen < 8192 ? 8192 : bufLen;
        this.buf = new byte[this.bufLen];
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException r) {
            throw new IOException(r);
        }
    }

    private void ensureOpen() throws IOException {
        if (in == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override
    public void close() throws IOException {
        if (in != null) {
            try {
                in.close();
            } finally {
                in = null;
            }
        }
    }

    @Override
    public int read() throws IOException {
        ensureOpen();
        if (bufin == null) {
            fill();
        }
        int r = bufin.read();
        if (r == -1) {
            if (reachEOF) {
                return -1;
            } else {
                fill();
                r = bufin.read();
            }
        }
        return r;
    }

    private void fill() throws IOException {
        int len = in.read(buf);
        if (len != -1) {
            byte[] bs = cipher.update(buf, 0, len);
            bufin = new ByteArrayInputStream(bs);
        } else {
            try {
                byte[] bs = cipher.doFinal();
                bufin = new ByteArrayInputStream(bs);
                reachEOF = true;
            } catch (BadPaddingException | IllegalBlockSizeException ex) {
                throw new IOException(ex);
            }
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (b == null) {
            throw new NullPointerException("Null buffer for read");
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
        if (bufin == null) {
            fill();
        }
        int rc = bufin.read(b, off, len);
        if (rc == -1) {
            if (reachEOF) {
                return -1;
            } else {
                fill();
                rc = bufin.read(b, off, len);
            }
        }
        return rc;
    }

    @Override
    public long skip(long n) throws IOException {
        for (int ii = 0; ii < n; ii++) {
            int rc = read();
            if (rc == -1) {
                return ii;
            }
        }
        return n;
    }

    @Override
    public int available() throws IOException {
        ensureOpen();
        if (reachEOF) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void mark(int limit) {
        //Operation not supported
    }

    @Override
    public void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }
}
