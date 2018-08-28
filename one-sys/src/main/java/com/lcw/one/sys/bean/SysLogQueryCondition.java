package com.lcw.one.sys.bean;

import com.lcw.one.util.http.bean.BaseQueryCondition;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.StringUtils;

import java.util.Map;

public class SysLogQueryCondition extends BaseQueryCondition {

    private String userId;
    private String httpMethod;
    private String httpUri;
    private String className;
    private Integer executeTime;
    private String createTimeFrom;
    private String createTimeTo;
    private Integer isFail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpUri() {
        return httpUri;
    }

    public void setHttpUri(String httpUri) {
        this.httpUri = httpUri;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Integer executeTime) {
        this.executeTime = executeTime;
    }

    public String getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(String createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public String getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(String createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public Integer getIsFail() {
        return isFail;
    }

    public void setIsFail(Integer isFail) {
        this.isFail = isFail;
    }

    @Override
    public void buildHQL(Map<String, Object> paramMap, StringBuilder hql) {
        if (StringUtils.isNotEmpty(getUserId())) {
            hql.append(" and userId = :userId");
            paramMap.put("userId", getUserId());
        }

        if (StringUtils.isNotEmpty(getHttpMethod())) {
            hql.append(" and httpMethod = :httpMethod");
            paramMap.put("httpMethod", getHttpMethod());
        }

        if (StringUtils.isNotEmpty(getHttpUri())) {
            hql.append(" and httpUri like :httpUri");
            paramMap.put("httpUri", "%" + getHttpUri() + "%");
        }

        if (StringUtils.isNotEmpty(getClassName())) {
            hql.append(" and className like :className");
            paramMap.put("className", "%" + getClassName() + "%");
        }

        if (getExecuteTime() != null) {
            hql.append(" and executeTime >= :executeTime");
            paramMap.put("executeTime", getExecuteTime());
        }

        if (StringUtils.isNotEmpty(getCreateTimeFrom())) {
            hql.append(" and createTime >= :createTimeFrom");
            paramMap.put("createTimeFrom", DateUtils.stringToDate(getCreateTimeFrom(), DateUtils.yyyy_MM_dd_EN));
        }

        if (StringUtils.isNotEmpty(getCreateTimeTo())) {
            hql.append(" and createTime <= :createTimeTo");
            paramMap.put("createTimeTo", DateUtils.stringToDate(getCreateTimeTo(), DateUtils.yyyy_MM_dd_EN));
        }

        if (getIsFail() != null) {
            hql.append(" and isFail >= :isFail");
            paramMap.put("isFail", getIsFail());
        }

        super.buildHQL(paramMap, hql);
    }
}