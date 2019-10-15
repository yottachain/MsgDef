package com.ytfs.common.net;

import static com.ytfs.common.net.P2PUtils.CONNECTS;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class P2PClientChecker extends Thread {

    private static final Logger LOG = Logger.getLogger(P2PClientChecker.class);

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                check();
                sleep(15000);
            } catch (InterruptedException e) {
                break;
            } catch (Throwable t) {
                try {
                    sleep(15000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }

    private void check() {
        List<P2PClient> ls = new ArrayList(CONNECTS.values());
        ls.stream().filter((client) -> (!client.isActive())).forEach((client) -> {
            P2PUtils.remove(client.getKey());
            LOG.info("Connection expired is recycled:" + client.getAddrs());
        });
    }
}
