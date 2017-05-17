package com.lcw.one.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-04-30.
 * @auth Licw
 */
public class UserExpertQualificationEOPK implements Serializable {
    private String userId;
    private String qualificationCode;

    @Column(name = "user_id")
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "qualification_code")
    @Id
    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExpertQualificationEOPK that = (UserExpertQualificationEOPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (qualificationCode != null ? !qualificationCode.equals(that.qualificationCode) : that.qualificationCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (qualificationCode != null ? qualificationCode.hashCode() : 0);
        return result;
    }
}
