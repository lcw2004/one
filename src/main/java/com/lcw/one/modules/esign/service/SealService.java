/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.esign.service;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.persistence.Parameter;
import com.lcw.one.common.service.BaseService;
import com.lcw.one.common.utils.CacheUtils;
import com.lcw.one.common.utils.StringUtils;
import com.lcw.one.modules.esign.dao.SealDao;
import com.lcw.one.modules.esign.entity.Seal;
import com.lcw.one.modules.sys.entity.Role;
import com.lcw.one.modules.sys.utils.DictUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2013-6-2
 */
@Service
@Transactional(readOnly = true)
public class SealService extends BaseService {

	@Autowired
	private SealDao sealDao;
	
	public Seal get(String id) {
		return sealDao.get(id);
	}

	@Transactional(readOnly = false)
	public void save(Seal seal) {
		sealDao.save(seal);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		sealDao.deleteById(id);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
	public Page<Seal> find(Page<Seal> page, Seal seal) {
		DetachedCriteria dc = sealDao.createDetachedCriteria();

		if(seal != null && StringUtils.isNotEmpty(seal.getSealToken())) {
			dc.add(Restrictions.like("sealToken", "%" + seal.getSealToken() + "%"));
		}
		if(seal != null && StringUtils.isNotEmpty(seal.getSealName())) {
			dc.add(Restrictions.like("sealName", "%" + seal.getSealName() + "%"));
		}

		dc.add(Restrictions.eq("delFlag", "0"));
		dc.addOrder(Order.desc("createDate"));
		return sealDao.find(page, dc);
	}

	public Seal findByToken(String sealToken) {
		return sealDao.getByHql("from Seal where delFlag = :p1 and sealToken = :p2", new Parameter(Role.DEL_FLAG_NORMAL, sealToken));
	}
	
}
