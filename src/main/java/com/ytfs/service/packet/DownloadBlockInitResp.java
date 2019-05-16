package com.ytfs.service.packet;

import io.yottachain.nodemgmt.core.vo.Node;

public class DownloadBlockInitResp {

    private Node[] nodes;
    private int VNF;
    private byte[][] VHF;
    private int[] nodeids;

    /**
     * @return the VHF
     */
    public byte[][] getVHF() {
        return VHF;
    }

    /**
     * @param VHF the VHF to set
     */
    public void setVHF(byte[][] VHF) {
        this.VHF = VHF;
    }

    /**
     * @return the nodes
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the VNF
     */
    public int getVNF() {
        return VNF;
    }

    /**
     * @param VNF the VNF to set
     */
    public void setVNF(int VNF) {
        this.VNF = VNF;
    }

    /**
     * @return the nodeids
     */
    public int[] getNodeids() {
        return nodeids;
    }

    /**
     * @param nodeids the nodeids to set
     */
    public void setNodeids(int[] nodeids) {
        this.nodeids = nodeids;
    }

}
