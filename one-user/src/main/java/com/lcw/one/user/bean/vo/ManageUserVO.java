package com.lcw.one.user.bean.vo;

import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.util.annotation.ExcelField;
import com.lcw.one.util.annotation.ExcelIndex;

public class ManageUserVO extends UserImportVO {

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
     * 电话
     */
    @ExcelField(title = "电话", column = 3)
    private String phone;

    /**
     * 手机
     */
    @ExcelField(title = "手机", column = 4, nullable = false)
    private String mobile;

    /**
     * 邮箱
     */
    @ExcelField(title = "邮箱", column = 5)
    private String email;

    /**
     * 备注
     */
    @ExcelField(title = "备注", column = 6)
    private String remark;

    private SysOfficeEO sysOffice;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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
