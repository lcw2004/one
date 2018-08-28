package com.lcw.one.sys.utils;

import com.lcw.one.sys.entity.SysAreaEO;

import java.util.Map;

public class SysAreaUtils {

    public static void buildHqlEqual(SysAreaEO sysAreaEO, StringBuilder hql, Map<String, Object> paramMap) {
        hql.append(" and c.areaId = :areaId");
        paramMap.put("areaId", sysAreaEO.getId());

    }

    public static void buildHqlLike(SysAreaEO sysAreaEO, StringBuilder hql, Map<String, Object> paramMap) {
        String parentIds = sysAreaEO.getParentIds() + sysAreaEO.getId() + ",";
        hql.append(" and ( c.areaId in ( select id from SysAreaEO where parentIds like :areaParentIds ) or c.areaId = :areaId ) ");
        paramMap.put("areaId", sysAreaEO.getId());
        paramMap.put("areaParentIds", parentIds + "%");
    }

}
