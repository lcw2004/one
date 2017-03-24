/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.AreaDao;
import com.lcw.one.sys.entity.Area;
import com.lcw.one.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends CrudService<AreaDao, Area> {

    @Transactional(readOnly = false)
    public void save(Area area) {
        area.setParent(this.get(area.getParent().getId()));
        String oldParentIds = area.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
        area.setParentIds(area.getParent().getParentIds() + area.getParent().getId() + ",");
        dao.clear();
        dao.save(area);
        // 更新子节点 parentIds
        List<Area> list = dao.findByParentIdsLike("%," + area.getId() + ",%");
        for (Area e : list) {
            e.setParentIds(e.getParentIds().replace(oldParentIds, area.getParentIds()));
        }
        dao.save(list);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        super.delete(id);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

    public Area organizeListAsTree(Area top, List<Area> list) {
        return new TreeEntityUtil<Area>().organizeListAsTree(top, list);
    }

}
