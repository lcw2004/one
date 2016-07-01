/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.esign.service;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.service.BaseService;
import com.lcw.one.common.utils.CacheUtils;
import com.lcw.one.common.utils.DateUtils;
import com.lcw.one.common.utils.StringUtils;
import com.lcw.one.modules.esign.dao.SealSignLogDao;
import com.lcw.one.modules.esign.entity.SealSignLog;
import com.lcw.one.modules.sys.utils.DictUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2013-6-2
 */
@Service
@Transactional(readOnly = true)
public class SealSignLogService extends BaseService {

	@Autowired
	private SealSignLogDao sealSignLogDao;
	
	public SealSignLog get(String id) {
		return sealSignLogDao.get(id);
	}

	@Transactional(readOnly = false)
	public void save(SealSignLog seal) {
		sealSignLogDao.save(seal);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		sealSignLogDao.deleteById(id);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	public Page<SealSignLog> find(Page<SealSignLog> page, Map<String, Object> paramMap) {
		DetachedCriteria dc = sealSignLogDao.createDetachedCriteria();

		String sealName = ObjectUtils.toString(paramMap.get("sealName"));
		if (StringUtils.isNotBlank(sealName)) {
			dc.add(Restrictions.like("sealName", "%" + sealName + "%"));
		}

		String signUserName = ObjectUtils.toString(paramMap.get("signUserName"));
		if (StringUtils.isNotBlank(signUserName)) {
			dc.add(Restrictions.like("signUserName", "%" + signUserName + "%"));
		}


		String fileName = ObjectUtils.toString(paramMap.get("fileName"));
		if (StringUtils.isNotBlank(fileName)) {
			dc.add(Restrictions.like("fileName", "%" + fileName + "%"));
		}

		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null){
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null){
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		dc.add(Restrictions.between("signTime", beginDate, endDate));

		dc.add(Restrictions.eq("delFlag", "0"));
		dc.addOrder(Order.desc("signTime"));
		return sealSignLogDao.find(page, dc);
	}
	
}
