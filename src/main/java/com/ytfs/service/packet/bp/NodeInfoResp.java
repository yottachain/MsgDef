package com.ytfs.service.packet.bp;

import io.yottachain.nodemgmt.core.vo.Node;

public class NodeInfoResp {

    private Node node;

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(Node node) {
        this.node = node;
    }

}
