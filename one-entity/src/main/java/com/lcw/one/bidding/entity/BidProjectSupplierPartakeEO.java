package com.lcw.one.bidding.entity;

import com.lcw.one.user.entity.UserSupplierEO;

import javax.persistence.*;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_project_supplier_partake")
@IdClass(BidProjectSupplierPartakeEOPK.class)
public class BidProjectSupplierPartakeEO {
    private String supplierId;
    private String projectId;
    private Integer canDownload;
    private Integer canUpload;
    private Integer partakeMethod;
    private Integer biddingStatus;
    private UserSupplierEO userSupplierEO;

    @Basic
    @Column(name = "can_download")
    public Integer getCanDownload() {
        return canDownload;
    }

    public void setCanDownload(Integer canDownload) {
        this.canDownload = canDownload;
    }

    @Basic
    @Column(name = "can_upload")
    public Integer getCanUpload() {
        return canUpload;
    }

    public void setCanUpload(Integer canUpload) {
        this.canUpload = canUpload;
    }

    @Basic
    @Column(name = "partake_method")
    public Integer getPartakeMethod() {
        return partakeMethod;
    }

    public void setPartakeMethod(Integer partakeType) {
        this.partakeMethod = partakeType;
    }

    @Basic
    @Column(name = "bidding_status")
    public Integer getBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(Integer biddingStatus) {
        this.biddingStatus = biddingStatus;
    }

    @Id
    @Column(name = "supplier_id")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Id
    @Column(name = "project_id")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    public UserSupplierEO getUserSupplierEO() {
        return userSupplierEO;
    }

    public void setUserSupplierEO(UserSupplierEO userSupplierEO) {
        this.userSupplierEO = userSupplierEO;
    }
}
