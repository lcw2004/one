package com.lcw.one.sys.entity;

import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_office")
public class SysOfficeEO extends TreeEntity<SysOfficeEO> {
    private String areaId;
    private String code;
    private Integer type;
    private Integer level;
    private String address;
    private String zipCode;
    private String masterId;
    private String phone;
    private String fax;
    private String email;
    private String remarks;

    private SysAreaEO area;
    private UserInfoEO masterUserInfo;

    @Basic
    @Column(name = "area_id")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "zip_code")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "master_id")
    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @ManyToOne
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    public SysAreaEO getArea() {
        return area;
    }

    public void setArea(SysAreaEO area) {
        this.area = area;
    }

    @ManyToOne
    @JoinColumn(name = "master_id", insertable = false, updatable = false)
    public UserInfoEO getMasterUserInfo() {
        return masterUserInfo;
    }

    public void setMasterUserInfo(UserInfoEO masterUserInfo) {
        this.masterUserInfo = masterUserInfo;
    }
}
