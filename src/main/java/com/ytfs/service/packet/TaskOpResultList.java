package com.ytfs.service.packet;

import java.util.List;

public class TaskOpResultList {

    private List<byte[]> id;
    private List<Integer> RES;

    /**
     * @return the id
     */
    public List<byte[]> getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(List<byte[]> id) {
        this.id = id;
    }

    /**
     * @return the RES
     */
    public List<Integer> getRES() {
        return RES;
    }

    /**
     * @param RES the RES to set
     */
    public void setRES(List<Integer> RES) {
        this.RES = RES;
    }

}
