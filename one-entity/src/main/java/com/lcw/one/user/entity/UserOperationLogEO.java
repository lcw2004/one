package com.lcw.one.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_operation_log")
public class UserOperationLogEO {
    private String userId;
    private String operateCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;
    private Integer result;
    private String remark;
    private String logId;
    private String ip;

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "operate_code")
    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Basic
    @Column(name = "operate_time")
    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Basic
    @Column(name = "result")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Id
    @Column(name = "log_id")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOperationLogEO that = (UserOperationLogEO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (operateCode != null ? !operateCode.equals(that.operateCode) : that.operateCode != null) return false;
        if (operateTime != null ? !operateTime.equals(that.operateTime) : that.operateTime != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = userId != null ? userId.hashCode() : 0;
        result1 = 31 * result1 + (operateCode != null ? operateCode.hashCode() : 0);
        result1 = 31 * result1 + (operateTime != null ? operateTime.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (remark != null ? remark.hashCode() : 0);
        result1 = 31 * result1 + (logId != null ? logId.hashCode() : 0);
        result1 = 31 * result1 + (ip != null ? ip.hashCode() : 0);
        return result1;
    }
}
