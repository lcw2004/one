package com.lcw.one.login.security.filterChain;

import com.lcw.one.util.utils.CollectionUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 安全框架角色资源配置服务类
 *
 * @author shadow
 */
public abstract class AbstractFilterChainDefinitionsService implements IFilterChainDefinitionsService {

    private final static Logger logger = LoggerFactory.getLogger(AbstractFilterChainDefinitionsService.class);

    private Map<String, String> defaultFilterChainDefinitionMap;
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @PostConstruct
    public void initPermission() {
        shiroFilterFactoryBean.setFilterChainDefinitionMap(CollectionUtils.clone(defaultFilterChainDefinitionMap));
        logger.debug("initialize shiro permission success...");
    }

    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            try {
                AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();

                // 获取过滤管理器
                PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

                // 清空初始权限配置
                manager.getFilterChains().clear();
                shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

                // 重新构建生成
                Map<String, String> chains = new LinkedHashMap<>();
                chains.putAll(initOtherPermission());
                chains.putAll(defaultFilterChainDefinitionMap);

                for (Map.Entry<String, String> entry : chains.entrySet()) {
                    String url = entry.getKey();
                    String chainDefinition = entry.getValue();
                    manager.createChain(url, chainDefinition);
                }

                logger.debug("update shiro permission success...");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public abstract Map<String, String> initOtherPermission();

    public void setDefaultFilterChainDefinitionMap(Map<String, String> defaultFilterChainDefinitionMap) {
        this.defaultFilterChainDefinitionMap = defaultFilterChainDefinitionMap;
    }

    public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
    }
}