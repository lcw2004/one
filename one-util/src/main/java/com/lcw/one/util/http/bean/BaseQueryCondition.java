package com.lcw.one.util.http.bean;

import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.http.PageInfo;

import java.util.Map;

public class BaseQueryCondition {

    private Integer pageNo;
    private Integer pageSize;
    private String orderBy;
    private Integer orderByType;
    private LoginUser currentUser;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(Integer orderByType) {
        this.orderByType = orderByType;
    }

    public PageInfo getPageInfo() {
        return new PageInfo(pageNo, pageSize);
    }

    public LoginUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(LoginUser currentUser) {
        this.currentUser = currentUser;
    }

    public void buildHQL(Map<String, Object> paramMap, StringBuilder hql) {
    }


}
