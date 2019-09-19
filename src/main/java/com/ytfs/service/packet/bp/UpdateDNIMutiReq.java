package com.ytfs.service.packet.bp;

import java.util.ArrayList;
import java.util.List;

public class UpdateDNIMutiReq {

    private List<UpdateDNIReq> list;

    /**
     * @return the list
     */
    public List<UpdateDNIReq> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<UpdateDNIReq> list) {
        this.list = list;
    }

    public void addList(List<UpdateDNIReq> list) {
        this.list = new ArrayList(list);
    }

}
