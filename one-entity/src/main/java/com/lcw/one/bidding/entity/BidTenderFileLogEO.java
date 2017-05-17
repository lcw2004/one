package com.lcw.one.bidding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_tender_file_log")
public class BidTenderFileLogEO {
    private String logId;
    private String userId;
    private String tenderFileId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;
    private String operateIp;
    private Integer operateType;

    @Id
    @Column(name = "log_id")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
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
    @Column(name = "tender_file_id")
    public String getTenderFileId() {
        return tenderFileId;
    }

    public void setTenderFileId(String tenderFileId) {
        this.tenderFileId = tenderFileId;
    }

    @Basic
    @Column(name = "operate_time")
    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Basic
    @Column(name = "operate_ip")
    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    @Basic
    @Column(name = "operate_type")
    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidTenderFileLogEO that = (BidTenderFileLogEO) o;

        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (tenderFileId != null ? !tenderFileId.equals(that.tenderFileId) : that.tenderFileId != null) return false;
        if (operateTime != null ? !operateTime.equals(that.operateTime) : that.operateTime != null) return false;
        if (operateIp != null ? !operateIp.equals(that.operateIp) : that.operateIp != null) return false;
        if (operateType != null ? !operateType.equals(that.operateType) : that.operateType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId != null ? logId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (tenderFileId != null ? tenderFileId.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        result = 31 * result + (operateIp != null ? operateIp.hashCode() : 0);
        result = 31 * result + (operateType != null ? operateType.hashCode() : 0);
        return result;
    }
}
