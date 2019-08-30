package com.ytfs.service.packet.bp;

import java.util.ArrayList;
import java.util.List;

public class AddDNIMutiReq {

    private List<AddDNIReq> list;

    /**
     * @return the list
     */
    public List<AddDNIReq> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<AddDNIReq> list) {
        this.list = list;
    }

    public void addList(List<AddDNIReq> list) {
        this.list = new ArrayList(list);
    }

}
