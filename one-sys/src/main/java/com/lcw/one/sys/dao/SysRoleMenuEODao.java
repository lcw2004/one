package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysRoleMenuEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SysRoleMenuEODao extends BaseRepositoryImpl<SysRoleMenuEO, String> {

    @Autowired
    public SysRoleMenuEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysRoleMenuEO.class, entityManager), entityManager);
    }

}