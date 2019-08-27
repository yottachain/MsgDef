package com.ytfs.service.packet;

public class UploadBlockSubResp {

    private ShardNode[] nodes;
    private ShardNode[] excessNodes;

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

    /**
     * @return the excessNodes
     */
    public ShardNode[] getExcessNodes() {
        return excessNodes;
    }

    /**
     * @param excessNodes the excessNodes to set
     */
    public void setExcessNodes(ShardNode[] excessNodes) {
        this.excessNodes = excessNodes;
    }

}
