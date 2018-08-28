package com.lcw.one.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcw.one.user.entity.UserInfoEO;

import javax.persistence.*;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 系统日志
 */
@Entity
@Table(name = "sys_log")
public class SysLogEO {

	/**
	 * 日志ID
	 */
	@Id
	@Column(name = "log_id")
	private String logId;

	/**
	 * 创建人ID
	 */
	@Basic
	@Column(name = "user_id")
	private String userId;

    /**
     * 操作名称
     */
    @Basic
    @Column(name = "operation_name")
    private String operationName;

	/**
	 * HTTP方法
	 */
	@Basic
	@Column(name = "http_method")
	private String httpMethod;

	/**
	 * HTTP URI
	 */
	@Basic
	@Column(name = "http_uri")
	private String httpUri;

	/**
	 * HTTP用户浏览器
	 */
	@Basic
	@Column(name = "http_user_agent")
	private String httpUserAgent;

	/**
	 * HTTP访问用户IP地址
	 */
	@Basic
	@Column(name = "http_remote_host")
	private String httpRemoteHost;

	/**
	 * HTTP 参数
	 */
	@Basic
	@Column(name = "http_paramters")
	private String httpParamters;

	/**
	 * 执行耗时（毫秒）
	 */
	@Basic
	@Column(name = "execute_time")
	private Integer executeTime;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Basic
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 执行类
	 */
	@Basic
	@Column(name = "class_name")
	private String className;

	/**
	 * 执行方法
	 */
	@Basic
	@Column(name = "method_name")
	private String methodName;

	/**
	 * 是否异常
	 */
	@Basic
	@Column(name = "is_fail")
	private Integer isFail;

	/**
	 * 备注
	 */
	@Basic
	@Column(name = "remark")
	private String remark;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserInfoEO userInfo;

    public String getLogId () {
        return this.logId;
    }

    public void setLogId (String logId) {
        this.logId = logId;
    }

    public String getUserId () {
        return this.userId;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public String getHttpMethod () {
        return this.httpMethod;
    }

    public void setHttpMethod (String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpUri () {
        return this.httpUri;
    }

    public void setHttpUri (String httpUri) {
        this.httpUri = httpUri;
    }

    public String getHttpUserAgent () {
        return this.httpUserAgent;
    }

    public void setHttpUserAgent (String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    public String getHttpRemoteHost () {
        return this.httpRemoteHost;
    }

    public void setHttpRemoteHost (String httpRemoteHost) {
        this.httpRemoteHost = httpRemoteHost;
    }

    public String getHttpParamters () {
        return this.httpParamters;
    }

    public void setHttpParamters (String httpParamters) {
        this.httpParamters = httpParamters;
    }

    public Integer getExecuteTime () {
        return this.executeTime;
    }

    public void setExecuteTime (Integer executeTime) {
        this.executeTime = executeTime;
    }

    public Date getCreateTime () {
        return this.createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }

    public String getClassName () {
        return this.className;
    }

    public void setClassName (String className) {
        this.className = className;
    }

    public String getMethodName () {
        return this.methodName;
    }

    public void setMethodName (String methodName) {
        this.methodName = methodName;
    }

    public String getRemark () {
        return this.remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }

	public UserInfoEO getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoEO userInfo) {
		this.userInfo = userInfo;
	}

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getIsFail() {
        return isFail;
    }

    public void setIsFail(Integer isFail) {
        this.isFail = isFail;
    }
}