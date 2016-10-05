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
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		DetachedCriteria dc = officeDao.createDetachedCriteria();
		dc.add(dataScopeFilter(UserUtils.getUser(), dc.getAlias(), ""));
		dc.add(Restrictions.eq(Office.FIELD_DEL_FLAG, Office.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("code"));
		List<Office> officeList = officeDao.find(dc);
		return officeList;
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

	/**
	 * 将菜单列表组织为菜单树
	 * @param topMenu
	 * @param list
	 * @return
	 */
	public Office organizeMenuListAsMenuTree(Office topMenu, List<Office> list) {
		// 按父ID将菜单归类
		Map<String, List<Office>> childMenuListMap = organizeListAsMapByParentId(list);

		// 递归组织菜单结构
		recursionChildMenuList(childMenuListMap, topMenu);

		return topMenu;
	}

	/**
	 * 将菜单列表重新组织为Map，以父ID为键，Menu List为值
	 *
	 * @param list
	 * @return
	 */
	private Map<String, List<Office>> organizeListAsMapByParentId(List<Office> list) {
		Map<String, List<Office>> childMenuListMap = new HashMap<>();
		for (Office menu : list) {
			if (StringUtils.isNotEmpty(menu.getParentId())) {
				String parentId = menu.getParentId();
				List<Office> menuList;
				if (childMenuListMap.containsKey(parentId)) {
					menuList = childMenuListMap.get(parentId);
				} else {
					menuList = new ArrayList<>();
				}

				menuList.add(menu);
				childMenuListMap.put(parentId, menuList);
			}

			menu.setChildList(null);
			menu.setParent(null);
		}
		return childMenuListMap;
	}

	/**
	 * 递归从Map中将Menu List设置到对应的Menu的childList属性中
	 *
	 * @param childMenuListMap
	 * @param parentMenu
	 */
	private void recursionChildMenuList(Map<String, List<Office>> childMenuListMap, Office parentMenu) {
		if(childMenuListMap != null && childMenuListMap.containsKey(parentMenu.getId())) {
			parentMenu.setChildList(childMenuListMap.get(parentMenu.getId()));
		}

		if(parentMenu.getChildList() != null &&parentMenu.getChildList().size() > 0) {
			for (Office childMenu : parentMenu.getChildList()) {
				recursionChildMenuList(childMenuListMap, childMenu);
			}
		}
	}
	
}
