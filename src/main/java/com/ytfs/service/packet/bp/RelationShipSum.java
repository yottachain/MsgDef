package com.ytfs.service.packet.bp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RelationShipSum {

    private List<String> mowner;
    private List<Long> usedspace;

    public void setValue(Map<String, Long> map) {
        Set<Map.Entry<String, Long>> set = map.entrySet();
        mowner = new ArrayList();
        usedspace = new ArrayList();
        for (Map.Entry<String, Long> ent : set) {
            mowner.add(ent.getKey());
            usedspace.add(ent.getValue());
        }
    }

    /**
     * @return the mowner
     */
    public List<String> getMowner() {
        return mowner;
    }

    /**
     * @param mowner the mowner to set
     */
    public void setMowner(List<String> mowner) {
        this.mowner = mowner;
    }

    /**
     * @return the usedspace
     */
    public List<Long> getUsedspace() {
        return usedspace;
    }

    /**
     * @param usedspace the usedspace to set
     */
    public void setUsedspace(List<Long> usedspace) {
        this.usedspace = usedspace;
    }

}
