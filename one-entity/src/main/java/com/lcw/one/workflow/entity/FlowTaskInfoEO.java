package com.lcw.one.workflow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.sys.entity.SysRoleEO;
import com.lcw.one.user.entity.UserInfoEO;

import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

/**
 * 工作流配置
 */
@Entity
@Table(name = "flow_task_info")
@IdClass(FlowTaskInfoEOPK.class)
public class FlowTaskInfoEO {

	/**
	 * 流程节点ID
	 */
	@Id
	@Column(name = "task_key")
	private String taskKey;

	/**
	 * 流程ID
	 */
	@Id
	@Column(name = "process_key")
	private String processKey;

	/**
	 * 任务名称
	 */
	@Basic
	@Column(name = "task_name")
	private String taskName;

	/**
	 * 描述
	 */
	@Basic
	@Column(name = "task_desc")
	private String taskDesc;

	/**
	 * 绑定对象类型
	 */
	@Basic
	@Column(name = "bind_type")
	private Integer bindType;

	/**
	 * 绑定角色
	 */
	@Basic
	@Column(name = "bind_role_id")
	private String bindRoleId;

	/**
	 * 绑定机构
	 */
	@Basic
	@Column(name = "bind_office_id")
	private String bindOfficeId;

	/**
	 * 绑定用户
	 */
	@Basic
	@Column(name = "bind_user_id")
	private String bindUserId;

	/**
	 * 绑定操作表单
	 */
	@Basic
	@Column(name = "bind_operation_form")
	private String bindOperationForm;

    /**
     * 绑定服务
     */
	@Basic
    @Column(name = "bind_service")
    private String bindService;

    @ManyToOne
    @JoinColumn(name = "bind_role_id", insertable = false, updatable = false)
    private SysRoleEO bindRole;

    @ManyToOne
    @JoinColumn(name = "bind_office_id", insertable = false, updatable = false)
    private SysOfficeEO bindOffice;

    @ManyToOne
    @JoinColumn(name = "bind_user_id", insertable = false, updatable = false)
    private UserInfoEO bindUser;

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

    public String getTaskName () {
        return this.taskName;
    }

    public void setTaskName (String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc () {
        return this.taskDesc;
    }

    public void setTaskDesc (String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getBindType () {
        return this.bindType;
    }

    public void setBindType (Integer bindType) {
        this.bindType = bindType;
    }

    public String getBindRoleId () {
        return this.bindRoleId;
    }

    public void setBindRoleId (String bindRoleId) {
        this.bindRoleId = bindRoleId;
    }

    public String getBindOfficeId () {
        return this.bindOfficeId;
    }

    public void setBindOfficeId (String bindOfficeId) {
        this.bindOfficeId = bindOfficeId;
    }

    public String getBindUserId () {
        return this.bindUserId;
    }

    public void setBindUserId (String bindUserId) {
        this.bindUserId = bindUserId;
    }

    public String getBindOperationForm () {
        return this.bindOperationForm;
    }

    public void setBindOperationForm (String bindOperationForm) {
        this.bindOperationForm = bindOperationForm;
    }

    public SysRoleEO getBindRole() {
        return bindRole;
    }

    public void setBindRole(SysRoleEO bindRole) {
        this.bindRole = bindRole;
    }

    public SysOfficeEO getBindOffice() {
        return bindOffice;
    }

    public void setBindOffice(SysOfficeEO bindOffice) {
        this.bindOffice = bindOffice;
    }

    public UserInfoEO getBindUser() {
        return bindUser;
    }

    public void setBindUser(UserInfoEO bindUser) {
        this.bindUser = bindUser;
    }

    public String getBindService() {
        return bindService;
    }

    public void setBindService(String bindService) {
        this.bindService = bindService;
    }
}