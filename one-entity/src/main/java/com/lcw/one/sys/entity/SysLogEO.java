package com.lcw.one.sys.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "sys_log")
public class SysLogEO {
    private String logId;
    private String httpMethod;
    private String httpUri;
    private String httpUserAgent;
    private String httpRemoteHost;
    private String httpParamters;
    private Date createTime;
    private String userId;
    private long executeTime;
    private String remark;

    @Id
    @Column(name = "log_id")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "http_method")
    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Basic
    @Column(name = "http_uri")
    public String getHttpUri() {
        return httpUri;
    }

    public void setHttpUri(String httpUri) {
        this.httpUri = httpUri;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "http_user_agent")
    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    @Basic
    @Column(name = "http_remote_host")
    public String getHttpRemoteHost() {
        return httpRemoteHost;
    }

    public void setHttpRemoteHost(String httpRemoteHost) {
        this.httpRemoteHost = httpRemoteHost;
    }

    @Basic
    @Column(name = "http_paramters")
    public String getHttpParamters() {
        return httpParamters;
    }

    public void setHttpParamters(String httpParamters) {
        this.httpParamters = httpParamters;
    }

    @Basic
    @Column(name = "execute_time")
    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
