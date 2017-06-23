package com.lcw.one.login.util;

import com.google.common.collect.Maps;
import com.lcw.one.login.security.SystemAuthorizingRealm;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.sys.entity.SysRoleEO;
import com.lcw.one.sys.service.SysMenuEOService;
import com.lcw.one.sys.service.SysRoleEOService;
import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.constant.GlobalConfig;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.ObjectUtils;
import com.lcw.one.util.utils.RedisUtil;
import com.lcw.one.util.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Map;

public class UserUtils {

    public static final String CURRENT_USER = "currentUser";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";
    public static final String CACHE_MENU_TREE = "menuTree";
    public static final String CACHE_AREA_LIST = "areaList";
    public static final String CACHE_OFFICE_LIST = "officeList";
    public static final  String LOGIN_FAIL_COUNT = "LOGIN_FAIL_COUNT";
    private static final Long LOGIN_FAIL_COUNT_LIMIT_TIME = 60 * 10L;

    private static UserInfoEOService userService = SpringContextHolder.getBean(UserInfoEOService.class);
    private static SysMenuEOService sysMenuService = SpringContextHolder.getBean(SysMenuEOService.class);
    private static SysRoleEOService sysRoleEOService = SpringContextHolder.getBean(SysRoleEOService.class);
    private static RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);

    /**
     * 退出
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

    /**
     * 获取当前登录用户ID
     *
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
     * 获取当前登录用户信息
     *
     */
    public static UserInfoEO getUser() {
        UserInfoEO user = (UserInfoEO) CacheUtils.getCache(CURRENT_USER);
        if (user == null) {
            String userId = getUserId();

            if(StringUtils.isNotEmpty(userId)) {
                UserInfoEO userInDb = userService.get(userId);
                user = ObjectUtils.clone(userInDb);
                user.setPassword(null);
                if(user.getUserContactInfo() == null) {
                    user.setUserContactInfo(new UserContactInfoEO());
                }

                CacheUtils.putCache(CURRENT_USER, user);
            }
        }
        return user;
    }

    public static void updateUserInfo(UserInfoEO userInfoEO) {
        UserInfoEO user = (UserInfoEO) CacheUtils.getCache(CURRENT_USER);
        if (user != null) {
            String userId = getUserId();

            if (StringUtils.isNotEmpty(userId)) {
                UserInfoEO userInDb = userService.get(userId);
                userInDb.setName(userInfoEO.getName());
                userInDb.setIdentityNumber(userInfoEO.getIdentityNumber());

                UserContactInfoEO userContactInfoEO = userInDb.getUserContactInfo();
                if(userContactInfoEO == null) {
                    userContactInfoEO = new UserContactInfoEO();
                    userContactInfoEO.setUserId(userId);
                }
                userContactInfoEO.setPhone(userInfoEO.getUserContactInfo().getPhone());
                userContactInfoEO.setMobile(userInfoEO.getUserContactInfo().getMobile());
                userContactInfoEO.setAddress(userInfoEO.getUserContactInfo().getAddress());
                userInDb.setUserContactInfo(userContactInfoEO);
                userService.save(userInDb);

                CacheUtils.removeCache(CURRENT_USER);
            }
        }
    }

    /**
     * 获取当前登录用户角色列表
     *
     */
    public static List<SysRoleEO> getRoleList() {
        List<SysRoleEO> roleList = (List<SysRoleEO>) CacheUtils.getCache(CACHE_ROLE_LIST);
        if (roleList == null) {
            UserInfoEO user = getUser();
            if(user != null) {
                roleList = sysRoleEOService.getSysRoleListByUserId(user.getUserId());
            }
            CacheUtils.putCache(CACHE_ROLE_LIST, roleList);
        }
        return roleList;
    }

    public static String getRoleIds() {
        List<SysRoleEO> roleList = getRoleList();
        if (CollectionUtils.isEmpty(roleList)) {
            return "";
        }
        StringBuilder roleIds = new StringBuilder();
        for (SysRoleEO sysRoleEO : roleList) {
            roleIds.append(sysRoleEO.getId()).append(",");
        }
        return roleIds.substring(0, roleIds.length() - 1);
    }


    /**
     * 获取当前登录用户菜单列表
     *
     */
    public static List<SysMenuEO> getMenuList() {
        List<SysMenuEO> menuList = (List<SysMenuEO>) CacheUtils.getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            UserInfoEO user = getUser();
            if (user == null) {
                return null;
            }

            if (isAdmin(user)) {
                menuList = sysMenuService.findAll();
            } else {
                menuList = sysMenuService.listSysMenuEOByUserId(user.getUserId());
            }
            CacheUtils.putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取当前登录用户菜单树
     *
     */
    public static SysMenuEO getMenuTree() {
        SysMenuEO menu = (SysMenuEO) CacheUtils.getCache(CACHE_MENU_TREE);
        if (menu == null) {
            List<SysMenuEO> sysMenuEOList = getMenuList();
            if (sysMenuEOList != null) {
                menu = sysMenuService.organizeListAsTree(sysMenuService.get("1"), sysMenuEOList);
                CacheUtils.putCache(CACHE_MENU_TREE, menu);
            }
        }
        return menu;
    }

    /**
     * 判断用户是否是超级管理员
     * @param userInfoEO
     * @return
     */
    public static boolean isAdmin(UserInfoEO userInfoEO) {
        return userInfoEO != null && userInfoEO.getUserId().equals("1");
    }

    /**
     * 获取用户菜单权限信息
     * @return
     */
    public static SimpleAuthorizationInfo getAuthInfo() {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysMenuEO> list = UserUtils.getMenuList();
        for (SysMenuEO menu : list) {
            if (StringUtils.isNotBlank(menu.getPermission())) {
                // 添加基于Permission的权限信息
                for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                    info.addStringPermission(permission);
                }
            }
        }
        return info;
    }

    /**
     * 更新用户的登录时间以及登录IP
     */
    public static void flushUserLoginTimeAndIp() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            String loginIp = SecurityUtils.getSubject().getSession().getHost();
            userService.updateUserLoginInfo(UserUtils.getUserId(), loginIp);
        }
    }

    public static void flush() {
        CacheUtils.removeCache(CURRENT_USER);
        CacheUtils.removeCache(CACHE_MENU_LIST);
        CacheUtils.removeCache(CACHE_MENU_TREE);
    }

    /**
     * 判断是否需要验证验证码
     *
     * @param userName
     * @return
     */
    public static boolean isNeedValidCode(String userName) {
        Integer loginFailNum = redisUtil.get(LOGIN_FAIL_COUNT + "_" + userName);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }

        return loginFailNum >= GlobalConfig.getMaxLoginErrorCount();
    }


    /**
     * 登录失败次数增加一次
     *
     * @param userName
     * @return
     */
    public static void increaseLoginErrorCount(String userName) {
        Integer loginFailNum = redisUtil.get(LOGIN_FAIL_COUNT + "_" + userName);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }

        loginFailNum++;
        redisUtil.set(LOGIN_FAIL_COUNT + "_" + userName, loginFailNum, LOGIN_FAIL_COUNT_LIMIT_TIME);
    }


    private static final class CacheUtils {
        public static Object getCache(String key) {
            return getCache(key, null);
        }

        public static Object getCache(String key, Object defaultValue) {
            Object obj = getCacheMap().get(key);
            return obj == null ? defaultValue : obj;
        }

        public static void putCache(String key, Object value) {
            getCacheMap().put(key, value);
        }

        public static void removeCache(String key) {
            getCacheMap().remove(key);
        }

        public static Map<String, Object> getCacheMap() {
            Map<String, Object> map = Maps.newHashMap();
            try {
                Subject subject = SecurityUtils.getSubject();
                SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
                return principal != null ? principal.getCacheMap() : map;
            } catch (UnavailableSecurityManagerException e) {

            } catch (InvalidSessionException e) {

            }
            return map;
        }
    };

}
