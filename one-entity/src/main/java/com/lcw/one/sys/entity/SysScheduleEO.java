package com.lcw.one.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

/**
 * 任务调度
 */
@Entity
@Table(name = "sys_schedule")
public class SysScheduleEO {

	/**
	 * 任务调度ID
	 */
	@Id
	@Column(name = "schedule_id")
	private String scheduleId;

	/**
	 * 任务名称
	 */
	@Basic
	@Column(name = "name")
	private String name;

	/**
	 * Cron表达式
	 */
	@Basic
	@Column(name = "expression")
	private String expression;

	/**
	 * 调用方式
	 */
	@Basic
	@Column(name = "type")
	private Integer type;

	/**
	 * 调用目标
	 */
	@Basic
	@Column(name = "service")
	private String service;

	/**
	 * 状态
	 */
	@Basic
	@Column(name = "status")
	private Integer status;

	/**
	 * 任务描述
	 */
	@Basic
	@Column(name = "remark")
	private String remark;


    public String getScheduleId () {
        return this.scheduleId;
    }

    public void setScheduleId (String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getExpression () {
        return this.expression;
    }

    public void setExpression (String expression) {
        this.expression = expression;
    }

    public Integer getType () {
        return this.type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public String getService () {
        return this.service;
    }

    public void setService (String service) {
        this.service = service;
    }

    public Integer getStatus () {
        return this.status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public String getRemark () {
        return this.remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }

}