package com.lcw.one.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_expert")
public class UserExpertEO {
    private String userId;
    private String orgName;
    private Integer isOrgUser;
    private Integer type;
    private String position;
    private String resumeFileId;
    private String professionLevel;
    private Date professionDate;
    private String remark;
    private String id;
    private String officeId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "org_name")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Basic
    @Column(name = "is_org_user")
    public Integer getIsOrgUser() {
        return isOrgUser;
    }

    public void setIsOrgUser(Integer isOrgUser) {
        this.isOrgUser = isOrgUser;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "resume_file_id")
    public String getResumeFileId() {
        return resumeFileId;
    }

    public void setResumeFileId(String resumeFileId) {
        this.resumeFileId = resumeFileId;
    }

    @Basic
    @Column(name = "profession_level")
    public String getProfessionLevel() {
        return professionLevel;
    }

    public void setProfessionLevel(String professionLevel) {
        this.professionLevel = professionLevel;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic
    @Column(name = "profession_date")
    public Date getProfessionDate() {
        return professionDate;
    }

    public void setProfessionDate(Date professionDate) {
        this.professionDate = professionDate;
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
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "office_id")
    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExpertEO that = (UserExpertEO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (isOrgUser != null ? !isOrgUser.equals(that.isOrgUser) : that.isOrgUser != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (resumeFileId != null ? !resumeFileId.equals(that.resumeFileId) : that.resumeFileId != null) return false;
        if (professionLevel != null ? !professionLevel.equals(that.professionLevel) : that.professionLevel != null)
            return false;
        if (professionDate != null ? !professionDate.equals(that.professionDate) : that.professionDate != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (officeId != null ? !officeId.equals(that.officeId) : that.officeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (isOrgUser != null ? isOrgUser.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (resumeFileId != null ? resumeFileId.hashCode() : 0);
        result = 31 * result + (professionLevel != null ? professionLevel.hashCode() : 0);
        result = 31 * result + (professionDate != null ? professionDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (officeId != null ? officeId.hashCode() : 0);
        return result;
    }
}
