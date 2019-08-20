package com.ytfs.service.packet;

public class TaskDispatchReq {

    private byte[] DNI;
    private int nodeId;
    private int execNodeId;

    /**
     * @return the DNI
     */
    public byte[] getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(byte[] DNI) {
        this.DNI = DNI;
    }

    /**
     * @return the nodeId
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the execNodeId
     */
    public int getExecNodeId() {
        return execNodeId;
    }

    /**
     * @param execNodeId the execNodeId to set
     */
    public void setExecNodeId(int execNodeId) {
        this.execNodeId = execNodeId;
    }

}
