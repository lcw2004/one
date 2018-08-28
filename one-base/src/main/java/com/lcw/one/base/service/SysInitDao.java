package com.lcw.one.base.service;

import com.lcw.one.sys.entity.SysConfigEO;
import com.lcw.one.sys.entity.SysDictDataEO;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SysInitDao extends BaseRepositoryImpl<SysConfigEO, String> {

    @Autowired
    public SysInitDao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysConfigEO.class, entityManager), entityManager);
    }

    public List<SysDictDataEO> listSysDictData() {
        return executeList(" from SysDictDataEO order by sort ");
    }

    public List<SysDictEO> listSysDict() {
        StringBuilder sql = new StringBuilder();
        sql.append(" from SysDictEO d ");
        sql.append(" where d.delFlag = ").append(DeleteFlagEnum.NORMAL.getValue());
        sql.append(" order by type ");
        return executeList(sql.toString());
    }

    public Map<String, String> getSysConfigMap() {
        Map<String, String> sysConfigMap = new HashMap<>();
        List<SysConfigEO> sysConfigEOList = this.executeList("from SysConfigEO");
        for (SysConfigEO sysConfig : sysConfigEOList) {
            sysConfigMap.put(sysConfig.getConfigKey(), sysConfig.getConfigValue());
        }
        return sysConfigMap;
    }

}