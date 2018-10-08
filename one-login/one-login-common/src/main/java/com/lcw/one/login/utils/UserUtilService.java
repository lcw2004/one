package com.lcw.one.login.utils;

import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.entity.UserExpertEO;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.service.UserExpertEOService;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.user.service.UserManagerEOService;
import com.lcw.one.user.service.UserSupplierEOService;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtilService {

    @Autowired
    private UserManagerEOService userManagerEOService;

    @Autowired
    private UserExpertEOService userExpertEOService;

    @Autowired
    private UserSupplierEOService userSupplierEOService;

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
        } else if (loginUser.getUserType() == UserInfoTypeEnum.EXPERT.getValue()) {
            // 获取机构信息
            UserExpertEO userExpertEO = userExpertEOService.get(loginUser.getUserId());
            if (userExpertEO != null && StringUtils.isNotEmpty(userExpertEO.getOfficeId())) {
                SysOfficeEO sysOfficeEO = userExpertEO.getSysOffice();
                if (sysOfficeEO != null) {
                    loginUser.setOfficeId(sysOfficeEO.getId());
                    loginUser.setOfficeParentIds(sysOfficeEO.getParentIds());
                }
            }
        } else if (loginUser.getUserType() == UserInfoTypeEnum.SUPPLIER.getValue()) {
            // 获取供应商信息
            UserSupplierEO supplier = userSupplierEOService.getByPrincipalUserId(loginUser.getUserId());
            loginUser.setSupplierId(supplier.getSupplierId());
        }
        loginUser.setDataScope(userInfoEOService.getMaxDataScope(loginUser.getUserId()));
    }
}
