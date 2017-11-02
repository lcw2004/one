package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysConfigEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class SysConfigEODao extends BaseRepositoryImpl<SysConfigEO, String> {

    @Autowired
    public SysConfigEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysConfigEO.class, entityManager), entityManager);
    }

    public List<SysConfigEO> list(String typeCode) {
        return list("from SysConfigEO where typeCode = ?1", typeCode);
    }

    public void update(String key, String value) {
        executeUpdate("update SysConfigEO set configValue = ?1 where configKey = ?2", value, key);
    }
}