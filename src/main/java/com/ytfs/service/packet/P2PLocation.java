package com.ytfs.service.packet;

import java.util.List;

public class P2PLocation {

    private String nodeId;
    private List<String> addrs;

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the addrs
     */
    public List<String> getAddrs() {
        return addrs;
    }

    /**
     * @param addrs the addrs to set
     */
    public void setAddrs(List<String> addrs) {
        this.addrs = addrs;
    }
}
