package com.lcw.one.user.bean.vo;

import com.lcw.one.util.annotation.ExcelField;
import com.lcw.one.util.annotation.ExcelIndex;

public class UserImportVO {

    /**
     * Excel中的数据行号
     */
    @ExcelIndex
    protected Integer index;

    /**
     * 登录账号
     */
    @ExcelField(title = "登录账号", column = 0, nullable = false)
    protected String account;

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
}
