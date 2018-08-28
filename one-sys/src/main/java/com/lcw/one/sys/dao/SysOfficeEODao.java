package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysOfficeEO;
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
public class SysOfficeEODao extends BaseRepositoryImpl<SysOfficeEO, String> {

    @Autowired
    public SysOfficeEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysOfficeEO.class, entityManager), entityManager);
    }

    public List<SysOfficeEO> findAll() {
        return executeList("from SysOfficeEO where delFlag = 0 order by code");
    }

    public List<SysOfficeEO> listByName(List<String> officeNameList) {
        return executeList("from SysOfficeEO where delFlag = 0 and name in ?1", officeNameList);
    }

    public SysOfficeEO getByCodeAndNameOfParent(String parentId, String code, String name, String excludeId) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" from SysOfficeEO where 1=1 ");

        if (StringUtils.isNotEmpty(parentId)) {
            hql.append(" and parentId = :parentId");
            paramMap.put("parentId", parentId);
        }
        if (StringUtils.isNotEmpty(code)) {
            hql.append(" and code = :code");
            paramMap.put("code", code);
        }
        if (StringUtils.isNotEmpty(name)) {
            hql.append(" and name = :name");
            paramMap.put("name", name);
        }
        if (StringUtils.isNotEmpty(excludeId)) {
            hql.append(" and id != :excludeId");
            paramMap.put("excludeId", excludeId);
        }
        return executeGet(hql.toString(), paramMap);
    }

}