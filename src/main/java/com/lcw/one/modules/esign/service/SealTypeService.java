/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.esign.service;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.service.BaseService;
import com.lcw.one.common.utils.CacheUtils;
import com.lcw.one.modules.esign.dao.SealTypeDao;
import com.lcw.one.modules.esign.entity.SealType;
import com.lcw.one.modules.sys.utils.DictUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2013-6-2
 */
@Service
@Transactional(readOnly = true)
public class SealTypeService extends BaseService {

	@Autowired
	private SealTypeDao sealTypeDao;
	
	public SealType get(String id) {
		return sealTypeDao.get(id);
	}

	@Transactional(readOnly = false)
	public void save(SealType seal) {
		sealTypeDao.save(seal);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		sealTypeDao.deleteById(id);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
	public Page<SealType> find(Page<SealType> page, Map<String, Object> paramMap) {
		DetachedCriteria dc = sealTypeDao.createDetachedCriteria();
		dc.addOrder(Order.desc("id"));
		return sealTypeDao.find(page, dc);
	}
	
}
