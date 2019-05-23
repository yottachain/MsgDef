package com.ytfs.service.packet;

import java.util.List;

public class QueryObjectMetaResp {

    private short[] blocknums;
    private long length;

    public QueryObjectMetaResp() {
    }

    public QueryObjectMetaResp(List<ObjectRefer> refers, long length) {
        this.length = length;
        if (refers.isEmpty()) {
            blocknums = new short[0];
        } else {
            int ii = 0;
            blocknums = new short[refers.size()];
            for (ObjectRefer refer : refers) {
                blocknums[ii++] = refer.getId();
            }
        }
    }

    /**
     * @return the blocknums
     */
    public short[] getBlocknums() {
        return blocknums;
    }

    /**
     * @param blocknums the blocknums to set
     */
    public void setBlocknums(short[] blocknums) {
        this.blocknums = blocknums;
    }

    /**
     * @return the length
     */
    public long getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }
}
