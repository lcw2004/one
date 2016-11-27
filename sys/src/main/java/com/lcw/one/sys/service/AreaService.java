/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.service;

import com.lcw.one.common.persistence.BaseDao;
import com.lcw.one.common.persistence.BaseEntity;
import com.lcw.one.sys.dao.AreaDao;
import com.lcw.one.sys.entity.Area;
import com.lcw.one.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends CrudService<AreaDao, Area>  {

	@Transactional(readOnly = false)
	public void save(Area area) {
		area.setParent(this.get(area.getParent().getId()));
		String oldParentIds = area.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		area.setParentIds(area.getParent().getParentIds()+area.getParent().getId()+",");
		dao.clear();
		dao.save(area);
		// 更新子节点 parentIds
		List<Area> list = dao.findByParentIdsLike("%,"+area.getId()+",%");
		for (Area e : list){
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

	/**
	 * 将菜单列表组织为菜单树
	 * @param topMenu
	 * @param list
	 * @return
	 */
	public Area organizeMenuListAsMenuTree(Area topMenu, List<Area> list) {
		// 按父ID将菜单归类
		Map<String, List<Area>> childMenuListMap = organizeListAsMapByParentId(list);

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
	private Map<String, List<Area>> organizeListAsMapByParentId(List<Area> list) {
		Map<String, List<Area>> childMenuListMap = new HashMap<>();
		for (Area menu : list) {
			if (StringUtils.isNotEmpty(menu.getParentId())) {
				String parentId = menu.getParentId();
				List<Area> menuList;
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
	private void recursionChildMenuList(Map<String, List<Area>> childMenuListMap, Area parentMenu) {
		if(childMenuListMap != null && childMenuListMap.containsKey(parentMenu.getId())) {
			parentMenu.setChildList(childMenuListMap.get(parentMenu.getId()));
		}

		if(parentMenu.getChildList() != null &&parentMenu.getChildList().size() > 0) {
			for (Area childMenu : parentMenu.getChildList()) {
				recursionChildMenuList(childMenuListMap, childMenu);
			}
		}
	}

}
