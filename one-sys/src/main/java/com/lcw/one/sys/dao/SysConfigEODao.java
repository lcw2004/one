package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysConfigEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysConfigEODao extends BaseRepositoryImpl<SysConfigEO, String> {

    @Autowired
    public SysConfigEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysConfigEO.class, entityManager), entityManager);
    }

    public PageInfo<SysConfigEO> page(PageInfo pageInfo, String configName, String configKey) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" from SysConfigEO where 1=1 ");
        if (StringUtils.isNotEmpty(configKey)) {
            hql.append(" and configKey like :configKey ");
            paramMap.put("configKey", "%" + configKey + "%");
        }
        if (StringUtils.isNotEmpty(configName)) {
            hql.append(" and configName like :configName ");
            paramMap.put("configName", "%" + configName + "%");
        }
        return super.page(pageInfo, hql.toString(), paramMap);
    }

    public List<SysConfigEO> list(String typeCode) {
        return super.list("from SysConfigEO where typeCode = ?1", typeCode);
    }

    public void update(String key, String value) {
        executeUpdate("update SysConfigEO set configValue = ?1 where configKey = ?2", value, key);
    }
}