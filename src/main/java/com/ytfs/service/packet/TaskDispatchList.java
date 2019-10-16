package com.ytfs.service.packet;

import java.util.List;

public class TaskDispatchList {

    private List<byte[]> DNI;
    private int nodeId;
    private int execNodeId;

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

    /**
     * @return the DNI
     */
    public List<byte[]> getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(List<byte[]> DNI) {
        this.DNI = DNI;
    }

    public void addDNI(byte[] dni) {
        this.DNI.add(dni);
    }

}
