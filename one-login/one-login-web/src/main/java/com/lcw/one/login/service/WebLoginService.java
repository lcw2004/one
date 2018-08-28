package com.lcw.one.login.service;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.login.security.ShiroSubjectUtils;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.utils.http.CookieUtils;
import com.lcw.one.util.utils.RequestUtils;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class WebLoginService {

    @Autowired
    private CommonLoginService commonLoginService;

    @Autowired
    private UserInfoEOService userInfoEOService;

    public String login(HttpServletRequest request) {
        String userId = ShiroSubjectUtils.getUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new LoginInvalidException();
        }

        UserInfoEO userInDb = userInfoEOService.get(userId);
        LoginUser loginUser = userInDb.toLoginUser();
        loginUser.setIp(RequestUtils.getClientIp(request));
        return commonLoginService.loginSuccess(loginUser);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        LoginUserUtils.clearAll(CookieUtils.getAuthToken(request));
        request.getSession().removeAttribute("token");
        CookieUtils.removeCookie(request, response);
        ShiroSubjectUtils.logout();
    }
}
