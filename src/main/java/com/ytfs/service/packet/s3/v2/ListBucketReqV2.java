package com.ytfs.service.packet.s3.v2;

import com.ytfs.service.packet.v2.AuthReq;

public class ListBucketReqV2 extends AuthReq {

    private boolean bool;

    /**
     * @return the bool
     */
    public boolean isBool() {
        return bool;
    }

    /**
     * @param bool the bool to set
     */
    public void setBool(boolean bool) {
        this.bool = bool;
    }

}
