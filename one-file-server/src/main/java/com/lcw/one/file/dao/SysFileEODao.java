package com.lcw.one.file.dao;

import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SysFileEODao extends BaseRepositoryImpl<SysFileEO, String> {

    @Autowired
    public SysFileEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysFileEO.class, entityManager), entityManager);
    }

    public PageInfo<SysFileEO> pageByStoreType(PageInfo pageInfo, String storeType, Date createTimeFrom) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" from SysFileEO where 1=1");
        if (StringUtils.isNotEmpty(storeType)) {
            hql.append(" and storeType = :storeType");
            paramMap.put("storeType", storeType);
        }
        if (createTimeFrom != null) {
            hql.append(" and createTime < :createTimeFrom");
            paramMap.put("createTimeFrom", createTimeFrom);
        }
        return executePage(pageInfo, hql.toString(), paramMap);
    }

    public void updateStoreType(String fileId, String storeType) {
        executeUpdate("update SysFileEO set storeType = ?1 where fileId = ?2", storeType, fileId);
    }
}