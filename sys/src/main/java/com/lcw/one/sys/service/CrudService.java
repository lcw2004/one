/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lcw.one.sys.service;

import java.util.List;

import com.lcw.one.common.persistence.BaseDao;
import com.lcw.one.common.persistence.BaseEntity;
import com.lcw.one.common.persistence.Page;
import com.lcw.one.sys.entity.DataEntity;
import com.lcw.one.sys.entity.Office;
import com.lcw.one.sys.entity.Role;
import com.lcw.one.sys.entity.User;
import com.lcw.one.sys.utils.UserUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends BaseDao<T>, T extends BaseEntity<T>> extends BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	@Transactional(readOnly = false)
	public void save(T entity) {
		dao.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		dao.deleteById(id);
	}
	
	public T get(String id) {
		return dao.get(id);
	}

	public List<T> queryAll() {
		DetachedCriteria dc = dao.createDetachedCriteria();
		dc.add(dataScopeFilter(UserUtils.getUser(), dc.getAlias(), ""));
		dc.add(Restrictions.eq(DataEntity.FIELD_DEL_FLAG, DataEntity.DEL_FLAG_NORMAL));
		List<T> list = dao.find(dc);
		return list;
	}

}
