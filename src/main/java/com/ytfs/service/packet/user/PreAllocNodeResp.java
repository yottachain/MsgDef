package com.ytfs.service.packet.user;

import java.util.ArrayList;
import java.util.List;

public class PreAllocNodeResp {

    private List<PreAllocNode> list;

    public PreAllocNodeResp() {
        list = new ArrayList();
    }

    public boolean addNode(PreAllocNode n) {
        boolean contains = false;
        for (PreAllocNode node : list) {
            if (node.getId() == n.getId()) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            list.add(n);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the list
     */
    public List<PreAllocNode> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<PreAllocNode> list) {
        this.list = list;
    }

}
