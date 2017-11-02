package com.lcw.one.workflow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flow_state")
public class FlowStateEO {
    private String processKey;
    private Integer processState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;
    private String processDefinitionId;
    private String processName;
    private List<FlowTaskInfoEO> flowTaskInfoEOList;

    @Id
    @Column(name = "process_key")
    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String pkProcessKey) {
        this.processKey = pkProcessKey;
    }

    @Basic
    @Column(name = "process_state")
    public Integer getProcessState() {
        return processState;
    }

    public void setProcessState(Integer processState) {
        this.processState = processState;
    }

    @Basic
    @Column(name = "last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Basic
    @Column(name = "process_definition_id")
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Basic
    @Column(name = "process_name")
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Transient
    public List<FlowTaskInfoEO> getFlowTaskInfoEOList() {
        return flowTaskInfoEOList;
    }

    public void setFlowTaskInfoEOList(List<FlowTaskInfoEO> flowTaskInfoEOList) {
        this.flowTaskInfoEOList = flowTaskInfoEOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlowStateEO that = (FlowStateEO) o;

        if (processKey != null ? !processKey.equals(that.processKey) : that.processKey != null) return false;
        if (processState != null ? !processState.equals(that.processState) : that.processState != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null) return false;
        if (processDefinitionId != null ? !processDefinitionId.equals(that.processDefinitionId) : that.processDefinitionId != null)
            return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processKey != null ? processKey.hashCode() : 0;
        result = 31 * result + (processState != null ? processState.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (processDefinitionId != null ? processDefinitionId.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        return result;
    }
}
