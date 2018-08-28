package com.lcw.one.sys.dao;

import com.lcw.one.sys.bean.SysLogQueryCondition;
import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class SysLogEODao extends BaseRepositoryImpl<SysLogEO, String> {

    @Autowired
    public SysLogEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysLogEO.class, entityManager), entityManager);
    }

    public PageInfo<SysLogEO> page(SysLogQueryCondition queryCondition) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" from SysLogEO where 1=1 ");
        queryCondition.buildHQL(paramMap, hql);
        hql.append(" order by createTime desc ");
        return page(queryCondition.getPageInfo(), hql.toString(), paramMap);
    }

}