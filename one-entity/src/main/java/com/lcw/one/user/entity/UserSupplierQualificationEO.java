package com.lcw.one.user.entity;

import com.lcw.one.baseInfo.entity.BaseQualificationTypeEO;

import javax.persistence.*;
import java.sql.Date;

/**
 * @version 2017-04-30.
 * @auth Licw
 */
@Entity
@Table(name = "user_supplier_qualification")
@IdClass(UserSupplierQualificationEOPK.class)
public class UserSupplierQualificationEO {
    private String supplierId;
    private String qualificationCode;
    private String fileId;
    private String issueAgency;
    private Date issueDate;
    private Date invalidDate;
    private String remark;
    private BaseQualificationTypeEO type;

    @Id
    @Column(name = "supplier_id")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Id
    @Column(name = "qualification_code")
    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    @Basic
    @Column(name = "file_id")
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "issue_agency")
    public String getIssueAgency() {
        return issueAgency;
    }

    public void setIssueAgency(String issueAgency) {
        this.issueAgency = issueAgency;
    }

    @Basic
    @Column(name = "issue_date")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Basic
    @Column(name = "invalid_date")
    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSupplierQualificationEO that = (UserSupplierQualificationEO) o;

        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;
        if (qualificationCode != null ? !qualificationCode.equals(that.qualificationCode) : that.qualificationCode != null)
            return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;
        if (issueAgency != null ? !issueAgency.equals(that.issueAgency) : that.issueAgency != null) return false;
        if (issueDate != null ? !issueDate.equals(that.issueDate) : that.issueDate != null) return false;
        if (invalidDate != null ? !invalidDate.equals(that.invalidDate) : that.invalidDate != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = supplierId != null ? supplierId.hashCode() : 0;
        result = 31 * result + (qualificationCode != null ? qualificationCode.hashCode() : 0);
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        result = 31 * result + (issueAgency != null ? issueAgency.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (invalidDate != null ? invalidDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "qualification_code", insertable = false, updatable = false)
    public BaseQualificationTypeEO getType() {
        return type;
    }

    public void setType(BaseQualificationTypeEO type) {
        this.type = type;
    }
}
