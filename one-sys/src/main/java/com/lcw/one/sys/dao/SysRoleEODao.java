package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysRoleEO;
import com.lcw.one.sys.entity.SysRoleMenuEO;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysRoleEODao extends BaseRepositoryImpl<SysRoleEO, String> {

    @Autowired
    private SysRoleMenuEODao sysRoleMenuEODao;

    @Autowired
    public SysRoleEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysRoleEO.class, entityManager), entityManager);
    }

    public PageInfo<SysRoleEO> page(PageInfo pageInfo, String roleName) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("from SysRoleEO where 1=1");
        sql.append(" and delFlag != " + DeleteFlagEnum.DELETE.getValue());
        if(StringUtils.isNotEmpty(roleName)) {
            sql.append(" and name like :roleName ");
            params.put("roleName", "%" + roleName + "%");
        }
        return page(pageInfo, sql.toString(), params);
    }

    public void deleteRoleMenuByRoleId(String roleId) {
        executeUpdate("delete from SysRoleMenuEO where roleId = ?1", roleId);
    }

    @Override
    public SysRoleEO save(SysRoleEO sysRoleEO) {
        super.save(sysRoleEO);

        deleteRoleMenuByRoleId(sysRoleEO.getId());
        if(CollectionUtils.isNotEmpty(sysRoleEO.getSysMenuEOIdList())) {
            for (String menuId : sysRoleEO.getSysMenuEOIdList()) {
                SysRoleMenuEO sysRoleMenuEO = new SysRoleMenuEO();
                sysRoleMenuEO.setMenuId(menuId);
                sysRoleMenuEO.setRoleId(sysRoleEO.getId());
                sysRoleMenuEODao.save(sysRoleMenuEO);
            }
        }

        return sysRoleEO;
    }

    public List<SysRoleEO> getSysRoleListByUserId(String userId) {
        return list("select r from SysRoleEO r, SysUserRoleEO ur where r.id = ur.roleId and ur.userId = ?1", userId);
    }
}