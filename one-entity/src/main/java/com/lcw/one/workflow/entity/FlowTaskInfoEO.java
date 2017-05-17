package com.lcw.one.workflow.entity;

import javax.persistence.*;

@Entity
@Table(name = "flow_task_info")
@IdClass(FlowTaskInfoEOPK.class)
public class FlowTaskInfoEO {
    private String processKey;
    private String taskKey;
    private String bindRole;
    private String bindForm;
    private String taskName;
    private Integer rankNum;
    private Integer valid;
    private String taskDesc;

    @Id
    @Column(name = "process_key")
    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String pkProcessKey) {
        this.processKey = pkProcessKey;
    }

    @Id
    @Column(name = "task_key")
    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String pkTaskKey) {
        this.taskKey = pkTaskKey;
    }

    @Basic
    @Column(name = "bind_role")
    public String getBindRole() {
        return bindRole;
    }

    public void setBindRole(String bindRole) {
        this.bindRole = bindRole;
    }

    @Basic
    @Column(name = "bind_form")
    public String getBindForm() {
        return bindForm;
    }

    public void setBindForm(String bindForm) {
        this.bindForm = bindForm;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "rank_num")
    public Integer getRankNum() {
        return rankNum;
    }

    public void setRankNum(Integer rankNum) {
        this.rankNum = rankNum;
    }

    @Basic
    @Column(name = "valid")
    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "task_desc")
    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlowTaskInfoEO that = (FlowTaskInfoEO) o;

        if (processKey != null ? !processKey.equals(that.processKey) : that.processKey != null) return false;
        if (taskKey != null ? !taskKey.equals(that.taskKey) : that.taskKey != null) return false;
        if (bindRole != null ? !bindRole.equals(that.bindRole) : that.bindRole != null) return false;
        if (bindForm != null ? !bindForm.equals(that.bindForm) : that.bindForm != null) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (rankNum != null ? !rankNum.equals(that.rankNum) : that.rankNum != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;
        if (taskDesc != null ? !taskDesc.equals(that.taskDesc) : that.taskDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processKey != null ? processKey.hashCode() : 0;
        result = 31 * result + (taskKey != null ? taskKey.hashCode() : 0);
        result = 31 * result + (bindRole != null ? bindRole.hashCode() : 0);
        result = 31 * result + (bindForm != null ? bindForm.hashCode() : 0);
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (rankNum != null ? rankNum.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (taskDesc != null ? taskDesc.hashCode() : 0);
        return result;
    }
}
