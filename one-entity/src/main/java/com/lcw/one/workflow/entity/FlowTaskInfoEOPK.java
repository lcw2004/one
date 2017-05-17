package com.lcw.one.workflow.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-05-10.
 * @auth Licw
 */
public class FlowTaskInfoEOPK implements Serializable {
    private String processKey;
    private String taskKey;

    @Column(name = "process_key")
    @Id
    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String pkProcessKey) {
        this.processKey = pkProcessKey;
    }

    @Column(name = "task_key")
    @Id
    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String pkTaskKey) {
        this.taskKey = pkTaskKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlowTaskInfoEOPK that = (FlowTaskInfoEOPK) o;

        if (processKey != null ? !processKey.equals(that.processKey) : that.processKey != null) return false;
        if (taskKey != null ? !taskKey.equals(that.taskKey) : that.taskKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processKey != null ? processKey.hashCode() : 0;
        result = 31 * result + (taskKey != null ? taskKey.hashCode() : 0);
        return result;
    }
}
