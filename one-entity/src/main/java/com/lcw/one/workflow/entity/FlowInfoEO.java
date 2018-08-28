package com.lcw.one.workflow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
import java.util.List;

/**
 * 工作流状态
 */
@Entity
@Table(name = "flow_info")
public class FlowInfoEO {

	/**
	 * 流程ID
	 */
	@Id
	@Column(name = "process_key")
	private String processKey;

	/**
	 * 流程状态
	 */
	@Basic
	@Column(name = "process_state")
	private Integer processState;

	/**
	 * 流程定义ID
	 */
	@Basic
	@Column(name = "process_definition_id")
	private String processDefinitionId;

	/**
	 * 流程名称
	 */
	@Basic
	@Column(name = "process_name")
	private String processName;

	/**
	 * 绑定表单
	 */
	@Basic
	@Column(name = "bind_view_form")
	private String bindViewForm;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Basic
	@Column(name = "last_update_time")
	private Date lastUpdateTime;

	@Transient
	private List<FlowTaskInfoEO> flowTaskList;

    public String getProcessKey () {
        return this.processKey;
    }

    public void setProcessKey (String processKey) {
        this.processKey = processKey;
    }

    public Integer getProcessState () {
        return this.processState;
    }

    public void setProcessState (Integer processState) {
        this.processState = processState;
    }

    public String getProcessDefinitionId () {
        return this.processDefinitionId;
    }

    public void setProcessDefinitionId (String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessName () {
        return this.processName;
    }

    public void setProcessName (String processName) {
        this.processName = processName;
    }

    public String getBindViewForm () {
        return this.bindViewForm;
    }

    public void setBindViewForm (String bindViewForm) {
        this.bindViewForm = bindViewForm;
    }

    public Date getLastUpdateTime () {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime (Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	public List<FlowTaskInfoEO> getFlowTaskList() {
		return flowTaskList;
	}

	public void setFlowTaskList(List<FlowTaskInfoEO> flowTaskList) {
		this.flowTaskList = flowTaskList;
	}
}