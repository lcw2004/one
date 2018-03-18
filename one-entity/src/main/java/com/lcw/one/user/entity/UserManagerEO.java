package com.lcw.one.user.entity;

import com.lcw.one.sys.entity.SysOfficeEO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_manager")
public class UserManagerEO {
    private String userId;
    private String officeId;
    private String remark;
    private String workNumber;
    private String password;

    private UserInfoEO userInfo;
    private SysOfficeEO sysOffice;
    private List<String> roleIdList = new ArrayList<>();
    private List<String> roleNameList = new ArrayList<>();

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

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "work_number")
    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public UserInfoEO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoEO userInfo) {
        this.userInfo = userInfo;
    }

    @OneToOne
    @JoinColumn(name = "office_id", insertable = false, updatable = false)
    public SysOfficeEO getSysOffice() {
        return sysOffice;
    }

    public void setSysOffice(SysOfficeEO sysOffice) {
        this.sysOffice = sysOffice;
    }

    @Transient
    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Transient
    public List<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<String> roleNameList) {
        this.roleNameList = roleNameList;
    }

    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
