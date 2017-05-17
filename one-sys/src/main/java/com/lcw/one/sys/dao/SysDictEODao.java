package com.lcw.one.sys.dao;

import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysDictEODao extends BaseRepositoryImpl<SysDictEO, String> {

    @Autowired
    public SysDictEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysDictEO.class, entityManager), entityManager);
    }

    public PageInfo<SysDictEO> page(PageInfo pageInfo, String type, String description) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("from SysDictEO where 1=1");
        if(StringUtils.isNotEmpty(type)) {
            sql.append(" and type = :type ");
            params.put("type", type);
        }
        if(StringUtils.isNotEmpty(description)) {
            sql.append(" and description like :description ");
            params.put("description", "%description%");
        }
        return page(pageInfo, sql.toString(), params);
    }

    public List<String> listDictType() {
        String sql = "select distinct(type) from SysDictEO";
        return list(sql);
    }

    @Override
    public List<SysDictEO> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" from SysDictEO d ");
        sql.append(" where d.delFlag = ").append(DeleteFlagEnum.NORMAL.getValue());
        return list(sql.toString());
    }
}