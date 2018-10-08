package com.lcw.one.login.service;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.login.utils.UserUtilService;
import com.lcw.one.user.constant.SupplierStatusEnum;
import com.lcw.one.user.constant.UserInfoStatusEnum;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.user.service.UserSupplierEOService;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonLoginService {

    @Autowired
    private UserInfoEOService userInfoEOService;

    @Autowired
    private UserUtilService userUtilService;

    @Autowired
    private UserSupplierEOService userSupplierEOService;

    public void validUser(LoginUser loginUser) {
        UserInfoEO userInfoEO = userInfoEOService.get(loginUser.getUserId());

        if (userInfoEO.getStatus() == UserInfoStatusEnum.STOP.getValue()) {
            throw new OneBaseException("用户的账户已经停用");
        }

        if (userInfoEO.getType() == UserInfoTypeEnum.SUPPLIER.getValue()) {
            UserSupplierEO supplier = userSupplierEOService.getByPrincipalUserId(loginUser.getUserId());
            userSupplierEOService.validSupplier(supplier);
        }
    }

    public String loginSuccess(LoginUser loginUser) {
        validUser(loginUser);

        // 更新用户的登录时间以及登录IP
        userInfoEOService.updateLoginIp(loginUser.getUserId(), loginUser.getIp());
        userUtilService.fetchOtherInfo(loginUser);

        // 缓存登录用户信息
        String token = UUID.randomUUID();
        LoginUserUtils.set(loginUser.getUserId() + LoginUserUtils.LOGIN_STATE, loginUser.getIp());
        LoginUserUtils.set(token + LoginUserUtils.LOGIN_USER_NEW, loginUser);
        if (loginUser.getUserType() == UserInfoTypeEnum.SUPPLIER.getValue()) {
            LoginUserUtils.set(loginUser.getSupplierId() + LoginUserUtils.LOGIN_STATE, loginUser.getIp());
        }
        return token;
    }

}
