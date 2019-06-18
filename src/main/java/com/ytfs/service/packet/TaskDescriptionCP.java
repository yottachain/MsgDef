package com.ytfs.service.packet;

import com.ytfs.common.SerializationStrategy;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeEnv;
import java.util.List;

public class TaskDescriptionCP implements SerializationStrategy {

    private long id;
    private byte[] dataHash;
    private List<P2PLocation> locations;

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
     * @return the dataHash
     */
    public byte[] getDataHash() {
        return dataHash;
    }

    /**
     * @param dataHash the dataHash to set
     */
    public void setDataHash(byte[] dataHash) {
        this.dataHash = dataHash;
    }

    /**
     * @return the locations
     */
    public List<P2PLocation> getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(List<P2PLocation> locations) {
        this.locations = locations;
    }

    @Override
    public IdStrategy getIdStrategy() {
        DefaultIdStrategy idStrategy = ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY);
        idStrategy.registerDelegate(new TaskDelegate());
        return idStrategy;
    }

}
