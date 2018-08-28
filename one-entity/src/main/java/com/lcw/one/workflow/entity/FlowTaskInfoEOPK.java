package com.lcw.one.workflow.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;

public class FlowTaskInfoEOPK implements Serializable {

    @Id
    @Column(name = "task_key")
    private String taskKey;

    @Id
    @Column(name = "process_key")
    private String processKey;

    public String getTaskKey () {
        return this.taskKey;
    }

    public void setTaskKey (String taskKey) {
        this.taskKey = taskKey;
    }

    public String getProcessKey () {
        return this.processKey;
    }

    public void setProcessKey (String processKey) {
        this.processKey = processKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlowTaskInfoEOPK that = (FlowTaskInfoEOPK) o;

        if (taskKey != null ? !taskKey.equals(that.taskKey) : that.taskKey != null) return false;
        if (processKey != null ? !processKey.equals(that.processKey) : that.processKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (taskKey != null ? taskKey.hashCode() : 0);
        result = 31 * result + (processKey != null ? processKey.hashCode() : 0);
        return result;
    }

}