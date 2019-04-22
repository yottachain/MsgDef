package com.ytfs.service.packet;

public class UploadBlockInitResp {

    private ShardNode[] nodes;
    private long VBI = 0;

    /**
     * @return the VBI
     */
    public long getVBI() {
        return VBI;
    }

    /**
     * @param VBI the VBI to set
     */
    public void setVBI(long VBI) {
        this.VBI = VBI;
    }

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
