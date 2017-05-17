package com.lcw.one.baseInfo.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "base_qualification_type")
public class BaseQualificationTypeEO {
    private Integer userType;
    private String qualificationCode;
    private String qualificationName;
    private Integer isNeed;

    @Basic
    @Column(name = "user_type")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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
    @Column(name = "qualification_name")
    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    @Basic
    @Column(name = "is_need")
    public Integer getIsNeed() {
        return isNeed;
    }

    public void setIsNeed(Integer isNeed) {
        this.isNeed = isNeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseQualificationTypeEO that = (BaseQualificationTypeEO) o;

        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (qualificationCode != null ? !qualificationCode.equals(that.qualificationCode) : that.qualificationCode != null)
            return false;
        if (qualificationName != null ? !qualificationName.equals(that.qualificationName) : that.qualificationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userType != null ? userType.hashCode() : 0;
        result = 31 * result + (qualificationCode != null ? qualificationCode.hashCode() : 0);
        result = 31 * result + (qualificationName != null ? qualificationName.hashCode() : 0);
        return result;
    }
}
