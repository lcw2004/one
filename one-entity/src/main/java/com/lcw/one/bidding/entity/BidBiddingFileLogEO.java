package com.lcw.one.bidding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_bidding_file_log")
public class BidBiddingFileLogEO {
    private String userId;
    private String biddingFileId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;
    private String operationIp;
    private Integer operationType;
    private String logId;

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "bidding_file_id")
    public String getBiddingFileId() {
        return biddingFileId;
    }

    public void setBiddingFileId(String biddingFileId) {
        this.biddingFileId = biddingFileId;
    }

    @Basic
    @Column(name = "operation_time")
    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Basic
    @Column(name = "operation_ip")
    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp;
    }

    @Basic
    @Column(name = "operation_type")
    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    @Id
    @Column(name = "log_id")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidBiddingFileLogEO that = (BidBiddingFileLogEO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (biddingFileId != null ? !biddingFileId.equals(that.biddingFileId) : that.biddingFileId != null)
            return false;
        if (operationTime != null ? !operationTime.equals(that.operationTime) : that.operationTime != null)
            return false;
        if (operationIp != null ? !operationIp.equals(that.operationIp) : that.operationIp != null) return false;
        if (operationType != null ? !operationType.equals(that.operationType) : that.operationType != null)
            return false;
        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (biddingFileId != null ? biddingFileId.hashCode() : 0);
        result = 31 * result + (operationTime != null ? operationTime.hashCode() : 0);
        result = 31 * result + (operationIp != null ? operationIp.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + (logId != null ? logId.hashCode() : 0);
        return result;
    }
}
