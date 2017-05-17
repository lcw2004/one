package com.lcw.one.user.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_manager")
public class UserManagerEO {
    private String userId;
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

        UserManagerEO that = (UserManagerEO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (officeId != null ? !officeId.equals(that.officeId) : that.officeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (officeId != null ? officeId.hashCode() : 0);
        return result;
    }
}
