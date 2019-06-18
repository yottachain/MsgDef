package com.ytfs.service.packet;

import com.ytfs.common.SerializationStrategy;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeEnv;
import java.util.List;

public class TaskDescription implements SerializationStrategy {

    private long id;
    private List<byte[]> dataHash;
    private List<byte[]> parityHash;
    private List<P2PLocation> locations;
    private List<Integer> recoverId;

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
    public List<byte[]> getDataHash() {
        return dataHash;
    }

    /**
     * @param dataHash the dataHash to set
     */
    public void setDataHash(List<byte[]> dataHash) {
        this.dataHash = dataHash;
    }

    /**
     * @return the parityHash
     */
    public List<byte[]> getParityHash() {
        return parityHash;
    }

    /**
     * @param parityHash the parityHash to set
     */
    public void setParityHash(List<byte[]> parityHash) {
        this.parityHash = parityHash;
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

    /**
     * @return the recoverId
     */
    public List<Integer> getRecoverId() {
        return recoverId;
    }

    /**
     * @param recoverId the recoverId to set
     */
    public void setRecoverId(List<Integer> recoverId) {
        this.recoverId = recoverId;
    }

    @Override
    public IdStrategy getIdStrategy() {
        DefaultIdStrategy idStrategy = ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY);
        idStrategy.registerDelegate(new TaskDelegate());
        return idStrategy;
    }
}
