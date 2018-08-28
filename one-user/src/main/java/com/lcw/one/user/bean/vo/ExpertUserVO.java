package com.lcw.one.user.bean.vo;

import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.util.annotation.ExcelField;

import java.util.Date;

public class ExpertUserVO extends UserImportVO {

    /**
     * 姓名
     */
    @ExcelField(title = "姓名", column = 1, nullable = false)
    private String name;

    /**
     * 归属单位/部门
     */
    @ExcelField(title = "归属单位/部门", column = 2)
    private String officeName;

    /**
     * 职务
     */
    @ExcelField(title = "职务", column = 3)
    private String position;


    /**
     * 性别
     */
    @ExcelField(title = "性别", column = 4)
    private String gender;

    /**
     * 出生年月
     */
    @ExcelField(title = "出生年月", column = 5)
    private Date birthday;

    /**
     * 专家类别
     */
    @ExcelField(title = "专家类别", column = 6)
    private String type;

    /**
     * 专家职称
     */
    @ExcelField(title = "专家职称", column = 7)
    private String professionTitle;

    /**
     * 手机
     */
    @ExcelField(title = "手机", column = 8)
    private String mobile;

    /**
     * 电话
     */
    @ExcelField(title = "电话", column = 9)
    private String phone;

    /**
     * 备注
     */
    @ExcelField(title = "备注", column = 10)
    private String remark;

    private SysOfficeEO sysOffice;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfessionTitle() {
        return professionTitle;
    }

    public void setProfessionTitle(String professionTitle) {
        this.professionTitle = professionTitle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SysOfficeEO getSysOffice() {
        return sysOffice;
    }

    public void setSysOffice(SysOfficeEO sysOffice) {
        this.sysOffice = sysOffice;
    }
}
