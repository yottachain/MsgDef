package com.ytfs.common.eos;

import com.ytfs.common.conf.ServerConfig;
import io.yottachain.nodemgmt.YottaNodeMgmt;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class BpList {

    private static final List<EOSURI> bplist = new ArrayList();

    private static EOSURI localURI;
    private static EOSURI nodeMgrURI;

    public static EOSURI getEOSURI() {
        if (!localURI.err.get()) {
            setNodeMgrURI(localURI);
            return localURI;
        } else {
            if (System.currentTimeMillis() - localURI.errTime.get() > 1000 * 60 * 60) {
                localURI.err.set(false);
                localURI.errTime.set(System.currentTimeMillis());
                setNodeMgrURI(localURI);
                return localURI;
            }
        }
        for (EOSURI eos : bplist) {
            if (!eos.err.get()) {
                setNodeMgrURI(eos);
                return eos;
            } else {
                if (System.currentTimeMillis() - eos.errTime.get() > 1000 * 60 * 60) {
                    eos.err.set(false);
                    eos.errTime.set(System.currentTimeMillis());
                    setNodeMgrURI(eos);
                    return eos;
                }
            }
        }
        setNodeMgrURI(localURI);
        return localURI;
    }

    private static void setNodeMgrURI(EOSURI eos) {
        if (eos != nodeMgrURI) {
            try {
                synchronized (BpList.class) {
                    YottaNodeMgmt.changeEosURL(eos.url);
                    nodeMgrURI = eos;
                }
            } catch (Throwable r) {
            }
        }
    }

    public static void init(List<String> addrs) {
        localURI = new EOSURI();
        localURI.url = ServerConfig.eosURI;
        if (addrs != null) {
            try {
                URL url = new URL(ServerConfig.eosURI);
                String localIp = url.getHost();
                for (String addr : addrs) {
                    String urll = ServerConfig.eosURI.replace(localIp, addr);
                    EOSURI uri = new EOSURI();
                    uri.url = urll;
                    bplist.add(uri);
                }
                Collections.shuffle(bplist);
            } catch (MalformedURLException ex) {
            }
        }
        nodeMgrURI = localURI;
    }

    public static class EOSURI {

        public String url;
        public AtomicBoolean err = new AtomicBoolean(false);
        public AtomicLong errTime = new AtomicLong(0);

        public boolean setErr(Exception r) {
            if (r.getMessage() != null && (r.getMessage().contains("java.net.ConnectException")
                    || r.getMessage().contains("Duplicate transaction")
                    || r.getMessage().contains("java.net.SocketTimeoutException"))) {
                if (!r.getMessage().contains("Duplicate transaction")) {
                    err.set(true);
                    errTime.set(System.currentTimeMillis());
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
                return true;
            }
            return false;
        }
    }
}
