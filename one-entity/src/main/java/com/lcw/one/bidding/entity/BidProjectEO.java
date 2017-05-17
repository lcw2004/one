package com.lcw.one.bidding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_project")
public class BidProjectEO {
    private String createUserId;
    private String purchaseOrgId;
    private String purchaseUserId;
    private String noticeId;
    private String projectId;
    private String projectName;
    private Integer projectType;
    private Integer chooseType;
    private Integer executionType;
    private Integer priceType;
    private String publicPrice;
    private String remark;
    private String qualificationRemark;
    private Integer subjectRatio;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buyEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentEndTime;
    private Double sellPrice;
    private Double bidPrice;
    private String openPassword;
    private Integer status;
    private String auditName;
    private Integer auditStatus;
    private String bidSummary;
    private Integer step;

    @Id
    @Column(name = "project_id")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String userId) {
        this.createUserId = userId;
    }

    @Basic
    @Column(name = "purchase_org_id")
    public String getPurchaseOrgId() {
        return purchaseOrgId;
    }

    public void setPurchaseOrgId(String orgId) {
        this.purchaseOrgId = orgId;
    }

    @Basic
    @Column(name = "purchase_user_id")
    public String getPurchaseUserId() {
        return purchaseUserId;
    }

    public void setPurchaseUserId(String purchaseUserId) {
        this.purchaseUserId = purchaseUserId;
    }

    @Basic
    @Column(name = "notice_id")
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @Basic
    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_type")
    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    @Basic
    @Column(name = "choose_type")
    public Integer getChooseType() {
        return chooseType;
    }

    public void setChooseType(Integer chooseType) {
        this.chooseType = chooseType;
    }

    @Basic
    @Column(name = "execution_type")
    public Integer getExecutionType() {
        return executionType;
    }

    public void setExecutionType(Integer executionType) {
        this.executionType = executionType;
    }

    @Basic
    @Column(name = "price_type")
    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    @Basic
    @Column(name = "public_price")
    public String getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(String publicPrice) {
        this.publicPrice = publicPrice;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "qualification_remark")
    public String getQualificationRemark() {
        return qualificationRemark;
    }

    public void setQualificationRemark(String qualificationRemark) {
        this.qualificationRemark = qualificationRemark;
    }

    @Basic
    @Column(name = "subject_ratio")
    public Integer getSubjectRatio() {
        return subjectRatio;
    }

    public void setSubjectRatio(Integer subjectRatio) {
        this.subjectRatio = subjectRatio;
    }

    @Basic
    @Column(name = "publish_time")
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "buy_end_time")
    public Date getBuyEndTime() {
        return buyEndTime;
    }

    public void setBuyEndTime(Date buyEndTime) {
        this.buyEndTime = buyEndTime;
    }

    @Basic
    @Column(name = "upload_end_time")
    public Date getUploadEndTime() {
        return uploadEndTime;
    }

    public void setUploadEndTime(Date uploadEndTime) {
        this.uploadEndTime = uploadEndTime;
    }

    @Basic
    @Column(name = "open_time")
    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    @Basic
    @Column(name = "comment_start_time")
    public Date getCommentStartTime() {
        return commentStartTime;
    }

    public void setCommentStartTime(Date commentStartTime) {
        this.commentStartTime = commentStartTime;
    }

    @Basic
    @Column(name = "comment_end_time")
    public Date getCommentEndTime() {
        return commentEndTime;
    }

    public void setCommentEndTime(Date commentEndTime) {
        this.commentEndTime = commentEndTime;
    }

    @Basic
    @Column(name = "sell_price")
    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Basic
    @Column(name = "bid_price")
    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    @Basic
    @Column(name = "open_password")
    public String getOpenPassword() {
        return openPassword;
    }

    public void setOpenPassword(String openPassword) {
        this.openPassword = openPassword;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "audit_name")
    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    @Basic
    @Column(name = "audit_status")
    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Basic
    @Column(name = "bid_summary")
    public String getBidSummary() {
        return bidSummary;
    }

    public void setBidSummary(String bidSummary) {
        this.bidSummary = bidSummary;
    }

    @Basic
    @Column(name = "step")
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

}
