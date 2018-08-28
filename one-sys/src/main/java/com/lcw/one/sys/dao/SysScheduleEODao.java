package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysScheduleEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class SysScheduleEODao extends BaseRepositoryImpl<SysScheduleEO, String> {

    @Autowired
    public SysScheduleEODao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysScheduleEO.class, entityManager), entityManager);
    }

    
}