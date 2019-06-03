package com.ytfs.service.packet;

public class UploadBlockSubResp {

    private ShardNode[] nodes;

    /**
     * @return the nodes
     */
    public ShardNode[] getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(ShardNode[] nodes) {
        this.nodes = nodes;
    }

}
