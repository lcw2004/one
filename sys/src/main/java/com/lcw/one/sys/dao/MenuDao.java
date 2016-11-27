/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.dao;

import com.lcw.one.common.persistence.BaseDao;
import com.lcw.one.common.persistence.Parameter;
import com.lcw.one.sys.entity.Dict;
import com.lcw.one.sys.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class MenuDao extends BaseDao<Menu> {

	public List<Menu> findByParentIdsLike(String parentIds){
		return find("from Menu where parentIds like :p1", new Parameter(parentIds));
	}

	public List<Menu> findByParentId(String parentId){
		return find("from Menu where parent.id = :p1", new Parameter(parentId));
	}

	public List<Menu> findByUserId(String userId){
		return find("select distinct m from Menu m, Role r, User u where m in elements (r.menuList) and r in elements (u.roleList)" +
				" and m.delFlag=:p1 and r.delFlag=:p1 and u.delFlag=:p1 and u.id=:p2" + // or (m.user.id=:p2  and m.delFlag=:p1)" + 
				" order by m.sort", new Parameter(Menu.DEL_FLAG_NORMAL, userId));
	}


}
