package com.lcw.one.login.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

public class ShiroSubjectUtils {

    /**
     * 获取当前登录用户ID
     */
    public static String getUserId() {
        String userId = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
            if (principal != null) {
                userId = principal.getId();
            }
        } catch (UnavailableSecurityManagerException e) {
        } catch (InvalidSessionException e) {
        }
        return userId;
    }

    /**
     * 退出系统
     */
    public static void logout() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (UnavailableSecurityManagerException e) {
            e.printStackTrace();
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
    }

}
