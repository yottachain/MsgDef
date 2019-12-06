package com.ytfs.service.packet;

import com.ytfs.common.SerializationStrategy;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeEnv;
import java.util.List;

public class TaskDescriptionLRC implements SerializationStrategy {

    private byte[] id;
    private List<byte[]> hashs;
    private List<P2PLocation> locations;
    private int parityShardCount;
    private int recoverId;

    @Override
    public IdStrategy getIdStrategy() {
        DefaultIdStrategy idStrategy = ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY);
        idStrategy.registerDelegate(new TaskDelegate());
        return idStrategy;
    }

    /**
     * @return the id
     */
    public byte[] getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(byte[] id) {
        this.id = id;
    }

    /**
     * @return the hashs
     */
    public List<byte[]> getHashs() {
        return hashs;
    }

    /**
     * @param hashs the hashs to set
     */
    public void setHashs(List<byte[]> hashs) {
        this.hashs = hashs;
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
    public int getRecoverId() {
        return recoverId;
    }

    /**
     * @param recoverId the recoverId to set
     */
    public void setRecoverId(int recoverId) {
        this.recoverId = recoverId;
    }

    /**
     * @return the parityShardCount
     */
    public int getParityShardCount() {
        return parityShardCount;
    }

    /**
     * @param parityShardCount the parityShardCount to set
     */
    public void setParityShardCount(int parityShardCount) {
        this.parityShardCount = parityShardCount;
    }
}
