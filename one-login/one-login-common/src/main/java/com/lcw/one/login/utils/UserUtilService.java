package com.lcw.one.login.utils;

import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.user.service.UserManagerEOService;
import com.lcw.one.util.bean.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtilService {

    @Autowired
    private UserManagerEOService userManagerEOService;

    @Autowired
    private UserInfoEOService userInfoEOService;

    public void fetchOtherInfo(LoginUser loginUser) {
        if (loginUser == null) {
            return;
        }
        if (loginUser.getUserType() == UserInfoTypeEnum.MANAGER.getValue()) {
            // 获取机构信息
            UserManagerEO userManagerEO = userManagerEOService.get(loginUser.getUserId());
            if (userManagerEO != null) {
                SysOfficeEO sysOfficeEO = userManagerEO.getSysOffice();
                if (sysOfficeEO != null) {
                    loginUser.setOfficeId(sysOfficeEO.getId());
                    loginUser.setOfficeParentIds(sysOfficeEO.getParentIds());
                }
            }
        }
        loginUser.setDataScope(userInfoEOService.getMaxDataScope(loginUser.getUserId()));
    }
}
