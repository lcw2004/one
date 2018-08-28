package com.lcw.one.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 任务调度日志
 */
@Entity
@Table(name = "sys_schedule_log")
public class SysScheduleLogEO {

	/**
	 * 日志ID
	 */
	@Id
	@Column(name = "log_id")
	private String logId;

	/**
	 * 调度时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Basic
	@Column(name = "schedule_time")
	private Date scheduleTime;

	/**
	 * 调用结果
	 */
	@Basic
	@Column(name = "result")
	private Integer result;

	/**
	 * 任务调度ID
	 */
	@Basic
	@Column(name = "schedule_id")
	private String scheduleId;


    public String getLogId () {
        return this.logId;
    }

    public void setLogId (String logId) {
        this.logId = logId;
    }

    public Date getScheduleTime () {
        return this.scheduleTime;
    }

    public void setScheduleTime (Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Integer getResult () {
        return this.result;
    }

    public void setResult (Integer result) {
        this.result = result;
    }

    public String getScheduleId () {
        return this.scheduleId;
    }

    public void setScheduleId (String scheduleId) {
        this.scheduleId = scheduleId;
    }

}