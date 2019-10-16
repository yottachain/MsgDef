package com.ytfs.service.packet;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<byte[]> tasks;

    /**
     * @return the tasks
     */
    public List<byte[]> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<byte[]> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(byte[] task) {
        if (this.tasks == null) {
            this.tasks = new ArrayList();
        }
        this.tasks.add(task);
    }

}
