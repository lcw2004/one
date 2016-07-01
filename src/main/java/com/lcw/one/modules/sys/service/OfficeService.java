/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.sys.service;

import com.lcw.one.common.service.BaseService;
import com.lcw.one.modules.sys.dao.OfficeDao;
import com.lcw.one.modules.sys.entity.Office;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends BaseService {

	@Autowired
	private OfficeDao officeDao;
	
	public Office get(String id) {
		return officeDao.get(id);
	}
	
	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		office.setParent(this.get(office.getParent().getId()));
		String oldParentIds = office.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		office.setParentIds(office.getParent().getParentIds()+office.getParent().getId()+",");
		officeDao.clear();
		officeDao.save(office);
		// 更新子节点 parentIds
		List<Office> list = officeDao.findByParentIdsLike("%,"+office.getId()+",%");
		for (Office e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, office.getParentIds()));
		}
		officeDao.save(list);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		officeDao.deleteById(id, "%,"+id+",%");
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
}
