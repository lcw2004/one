package com.lcw.one.sys.dao;

import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class SysMenuEODao extends BaseRepositoryImpl<SysMenuEO, String> {

    @Autowired
    public SysMenuEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysMenuEO.class, entityManager), entityManager);
    }

    public List<SysMenuEO> listSysMenuEOByUserId(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct m from SysMenuEO m, SysRoleMenuEO rm, SysUserRoleEO ur ");
        sql.append(" where m.id = rm.menuId ");
        sql.append(" and ur.roleId = rm.roleId ");
        sql.append(" and m.delFlag = ").append(DeleteFlagEnum.NORMAL.getValue());
        sql.append(" and ur.userId = ?1");
        sql.append(" order by sort ");
        return list(sql.toString(), userId);
    }

    public List<SysMenuEO> listSysMenuEOByRoleId(String roleId) {
        return list("select distinct m from SysMenuEO m, SysRoleMenuEO rm where m.id = rm.menuId and rm.roleId = ?1 order by sort", roleId);
    }

    @Override
    public List<SysMenuEO> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct m from SysMenuEO m ");
        sql.append(" where m.delFlag = ").append(DeleteFlagEnum.NORMAL.getValue());
        sql.append(" order by sort ");
        return list(sql.toString());
    }

    public PageInfo<SysMenuEO> page(PageInfo pageInfo) {
        return page(pageInfo, "from SysMenuEO");
    }

    public void deleteByParentIds(String parentIds) {
        String hql = "update SysMenuEO set delFlag = '" + DeleteFlagEnum.DELETE.getValue() + "' where id parentIds like ?";
        executeUpdate(hql, parentIds + "%");
    }

    public void updateSort(String id, Integer sort) {
        executeUpdate("update SysMenuEO set sort = ?1 where id = ?2", sort, id);
    }
}