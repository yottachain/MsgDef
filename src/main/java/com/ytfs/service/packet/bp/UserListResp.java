package com.ytfs.service.packet.bp;

import java.util.ArrayList;
import java.util.List;

public class UserListResp {

    private List<UserSpace> list;

    /**
     * @return the list
     */
    public List<UserSpace> getList() {
        return list;
    }

    public void addList(UserSpace space) {
        if (this.list == null) {
            this.list = new ArrayList();
        }
        this.list.add(space);
    }

    /**
     * @param list the list to set
     */
    public void setList(List<UserSpace> list) {
        this.list = list;
    }

}
