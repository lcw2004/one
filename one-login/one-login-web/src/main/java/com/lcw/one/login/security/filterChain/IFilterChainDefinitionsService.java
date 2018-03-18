package com.lcw.one.login.security.filterChain;

import java.util.Map;

public interface IFilterChainDefinitionsService {

    /**
     * 初始化框架权限资源配置
     */
    void initPermission();

    /**
     * 重新加载框架权限资源配置 (强制线程同步)
     */
    void updatePermission();

    /**
     * 初始化第三方权限资源配置
     */
    Map<String, String> initOtherPermission();
}
