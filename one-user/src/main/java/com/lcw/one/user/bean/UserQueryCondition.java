package com.lcw.one.user.bean;

import com.lcw.one.util.http.bean.BaseQueryCondition;
import com.lcw.one.util.utils.StringUtils;

import java.util.Map;

/**
 * @version 2017-04-30.
 * @auth Licw
 */
public class UserQueryCondition extends BaseQueryCondition {

    // UserInfo
    private String userLikeName;
    private String userAccount;
    private Integer userStatus;

    // UserContractInfo
    private String userAreaCode;
    private String userMobile;
    private String userEmail;
    private String userPhone;
    private String userWechatId;
    private String userAddress;
    private String userZipCode;

    // UserBaseInfo
    private Integer userGender;
    private String userIdentityNumber;

    public String getUserLikeName() {
        return userLikeName;
    }

    public void setUserLikeName(String userLikeName) {
        this.userLikeName = userLikeName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserAreaCode() {
        return userAreaCode;
    }

    public void setUserAreaCode(String userAreaCode) {
        this.userAreaCode = userAreaCode;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserWechatId() {
        return userWechatId;
    }

    public void setUserWechatId(String userWechatId) {
        this.userWechatId = userWechatId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserZipCode() {
        return userZipCode;
    }

    public void setUserZipCode(String userZipCode) {
        this.userZipCode = userZipCode;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserIdentityNumber() {
        return userIdentityNumber;
    }

    public void setUserIdentityNumber(String userIdentityNumber) {
        this.userIdentityNumber = userIdentityNumber;
    }

    @Override
    public void buildHQL(Map<String, Object> paramMap, StringBuilder hql) {
        if (StringUtils.isNotEmpty(getUserLikeName())) {
            hql.append(" and (" );
            hql.append("    u.userInfo.name like :userLikeName ");
            hql.append("    or u.userInfo.account like :userLikeName ");
            hql.append("    or u.userInfo.userContactInfo.mobile like :userLikeName ");
            hql.append("    or u.userInfo.userContactInfo.phone like :userLikeName ");
            hql.append(" ) ");
            paramMap.put("userLikeName", "%" + getUserLikeName() + "%");
        }

    }
}
