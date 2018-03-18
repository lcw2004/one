package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysHomeComponentEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.Cacheable;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysHomeComponentEODao extends BaseRepositoryImpl<SysHomeComponentEO, String> {

    @Autowired
    public SysHomeComponentEODao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysHomeComponentEO.class, entityManager), entityManager);
    }

    public List<SysHomeComponentEO> listAll() {
        return listAll();
    }

    public List<String> listByRoleId(String roleId) {
        return executeList("select distinct rc.componentId from SysRoleComponentEO rc where rc.roleId = ?1", roleId);
    }

    public List<SysHomeComponentEO> listByUserId(String userId) {
        return list("select distinct c from SysHomeComponentEO c, SysRoleComponentEO rc, SysUserRoleEO ur where c.componentId = rc.componentId and rc.roleId = ur.roleId and ur.userId = ?1", userId);
    }
    
}