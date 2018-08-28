package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysDictDataEO;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysDictDataEODao extends BaseRepositoryImpl<SysDictDataEO, String> {

    @Autowired
    public SysDictDataEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysDictDataEO.class, entityManager), entityManager);
    }

    @Override
    public List<SysDictDataEO> findAll() {
        return executeList(" from SysDictDataEO order by sort ");
    }

    public List<SysDictDataEO> list(String dictId) {
        return executeList("from SysDictDataEO where dictId = ?1 order by sort", dictId);
    }

    public void deleteByDictId(String dictId) {
        executeUpdate("delete from SysDictDataEO where dictId = ?1 order by sort", dictId);
    }

    public Integer getNextSort(String dictId) {
        Integer sort = executeGet("select max(sort) + 1 from SysDictDataEO where dictId = ?1", dictId);
        if (sort == null) {
            sort = 1;
        }
        return sort;
    }

}