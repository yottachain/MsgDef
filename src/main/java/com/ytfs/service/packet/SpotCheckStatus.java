package com.ytfs.service.packet;

import java.util.List;

public class SpotCheckStatus {
    private String taskId;
    private int percent;
    private List<Integer> invalidNodeList; 

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
     * @return the percent
     */
    public int getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(int percent) {
        this.percent = percent;
    }

    /**
     * @return the invalidNodeList
     */
    public List<Integer> getInvalidNodeList() {
        return invalidNodeList;
    }

    /**
     * @param invalidNodeList the invalidNodeList to set
     */
    public void setInvalidNodeList(List<Integer> invalidNodeList) {
        this.invalidNodeList = invalidNodeList;
    }
    
}
