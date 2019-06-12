package com.ytfs.service.packet;

public class TaskOpResult {

    public static final int RES_OK = 0;
    public static final int RES_ERR = 100;

    private long id;
    private int RES;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the RES
     */
    public int getRES() {
        return RES;
    }

    /**
     * @param RES the RES to set
     */
    public void setRES(int RES) {
        this.RES = RES;
    }

}
