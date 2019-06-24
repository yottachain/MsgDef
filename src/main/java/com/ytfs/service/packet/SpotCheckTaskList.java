package com.ytfs.service.packet;

import com.ytfs.common.SerializationStrategy;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeEnv;
import java.util.List;

public class SpotCheckTaskList implements SerializationStrategy {

    private String taskId;
    private int snid;
    private List<SpotCheckTask> taskList;

    /**
     * @return the snid
     */
    public int getSnid() {
        return snid;
    }

    /**
     * @param snid the snid to set
     */
    public void setSnid(int snid) {
        this.snid = snid;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskList
     */
    public List<SpotCheckTask> getTaskList() {
        return taskList;
    }

    /**
     * @param taskList the taskList to set
     */
    public void setTaskList(List<SpotCheckTask> taskList) {
        this.taskList = taskList;
    }

    @Override
    public IdStrategy getIdStrategy() {
        DefaultIdStrategy idStrategy = ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY);
        idStrategy.registerDelegate(new SpotCheckTaskDelegate());
        return idStrategy;
    }

}
