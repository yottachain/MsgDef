package com.ytfs.service.packet.bp;

import io.yottachain.nodemgmt.core.vo.Node;
import java.util.List;

public class NodeSyncReq {

    private List<Node> node;

    /**
     * @return the node
     */
    public List<Node> getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(List<Node> node) {
        this.node = node;
    }

}
