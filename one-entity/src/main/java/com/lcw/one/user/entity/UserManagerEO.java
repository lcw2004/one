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

    private UserInfoEO userInfo;

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

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    public UserInfoEO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoEO userInfo) {
        this.userInfo = userInfo;
    }
}
