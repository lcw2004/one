/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.service;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.util.CacheUtils;
import com.lcw.one.sys.dao.AreaDao;
import com.lcw.one.sys.dao.DictDao;
import com.lcw.one.sys.entity.Area;
import com.lcw.one.sys.entity.Dict;
import com.lcw.one.sys.utils.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

    public Page<Dict> find(Page<Dict> page, Dict dict) {
        // Hibernate 查询
        DetachedCriteria dc = dao.createDetachedCriteria();
        if (StringUtils.isNotEmpty(dict.getType())) {
            dc.add(Restrictions.eq("type", dict.getType()));
        }
        if (StringUtils.isNotEmpty(dict.getDescription())) {
            dc.add(Restrictions.like("description", "%" + dict.getDescription() + "%"));
        }
        dc.add(Restrictions.eq(Dict.FIELD_DEL_FLAG, Dict.DEL_FLAG_NORMAL));
        dc.addOrder(Order.asc("type")).addOrder(Order.asc("sort")).addOrder(Order.desc("id"));
        return dao.find(page, dc);
    }

    public List<String> findTypeList() {
        return dao.findTypeList();
    }

    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        super.delete(id);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

}
