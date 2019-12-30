package com.ytfs.service.packet.node;

import java.util.ArrayList;
import java.util.List;

public class ListDNIResp {

    private List<byte[]> vhfList;
    private String nextId;
    

    public void addVHF(byte[] bs) {
        if (vhfList == null) {
            vhfList = new ArrayList();
        }
        vhfList.add(bs);
    }

    /**
     * @return the nextId
     */
    public String getNextId() {
        return nextId;
    }

    /**
     * @param nextId the nextId to set
     */
    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    /**
     * @return the vhfList
     */
    public List<byte[]> getVhfList() {
        return vhfList;
    }

    /**
     * @param vhfList the vhfList to set
     */
    public void setVhfList(List<byte[]> vhfList) {
        this.vhfList = vhfList;
    }
}
