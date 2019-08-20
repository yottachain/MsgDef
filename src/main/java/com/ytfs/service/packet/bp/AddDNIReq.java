package com.ytfs.service.packet.bp;

public class AddDNIReq {

    private byte[] dni;
    private int nodeid;
    private boolean delete;

    /**
     * @return the dni
     */
    public byte[] getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(byte[] dni) {
        this.dni = dni;
    }

    /**
     * @return the nodeid
     */
    public int getNodeid() {
        return nodeid;
    }

    /**
     * @param nodeid the nodeid to set
     */
    public void setNodeid(int nodeid) {
        this.nodeid = nodeid;
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

}
