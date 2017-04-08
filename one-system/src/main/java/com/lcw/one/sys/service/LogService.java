/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.service;

import com.lcw.one.common.util.DateUtils;
import com.lcw.one.common.util.StringUtils;
import com.lcw.one.sys.dao.LogDao;
import com.lcw.one.sys.entity.Log;
import com.lcw.one.util.bean.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
public class LogService extends CrudService<LogDao, Log> {

    public Page<Log> find(Page<Log> page, Map<String, Object> paramMap) {
        DetachedCriteria dc = dao.createDetachedCriteria();

        String createByName = ObjectUtils.toString(paramMap.get("createByName"));
        if (StringUtils.isNotEmpty(createByName)) {
            dc.createAlias("createBy", "createByAlias");
            dc.add(Restrictions.disjunction().
                    add(Restrictions.eq("createByAlias.name", createByName)).
                    add(Restrictions.eq("createByAlias.loginName", createByName)));
        }

        String requestUri = ObjectUtils.toString(paramMap.get("requestUri"));
        if (StringUtils.isNotBlank(requestUri)) {
            dc.add(Restrictions.like("requestUri", "%" + requestUri + "%"));
        }

        String exception = ObjectUtils.toString(paramMap.get("exception"));
        if (StringUtils.isNotBlank(exception)) {
            dc.add(Restrictions.eq("type", Log.TYPE_EXCEPTION));
        }

        Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
        if (beginDate == null) {
            beginDate = DateUtils.setDays(new Date(), 1);
            paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
        }
        Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
        if (endDate == null) {
            endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
            paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
        }
        dc.add(Restrictions.between("createDate", beginDate, endDate));
        dc.addOrder(Order.desc("createDate"));
        return dao.find(page, dc);
    }

}
