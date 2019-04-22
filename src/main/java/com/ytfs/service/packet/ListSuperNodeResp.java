package com.ytfs.service.packet;

import io.yottachain.nodemgmt.core.vo.SuperNode;

public class ListSuperNodeResp {

    /**
     * @return the superList
     */
    public SuperNode[] getSuperList() {
        return superList;
    }

    /**
     * @param superList the superList to set
     */
    public void setSuperList(SuperNode[] superList) {
        this.superList = superList;
    }
    private SuperNode[] superList;
}
