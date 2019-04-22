package com.ytfs.service.packet;

public class UploadBlockSubResp {

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
    private ShardNode[] nodes;

}
