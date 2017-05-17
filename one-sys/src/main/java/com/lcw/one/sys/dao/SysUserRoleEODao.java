package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysUserRoleEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SysUserRoleEODao extends BaseRepositoryImpl<SysUserRoleEO, String> {

    @Autowired
    public SysUserRoleEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysUserRoleEO.class, entityManager), entityManager);
    }

}