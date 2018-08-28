package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysScheduleLogEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class SysScheduleLogEODao extends BaseRepositoryImpl<SysScheduleLogEO, String> {

    @Autowired
    public SysScheduleLogEODao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysScheduleLogEO.class, entityManager), entityManager);
    }

    
}