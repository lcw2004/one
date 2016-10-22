/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.sys.web;

import com.lcw.one.common.utils.DateUtils;
import com.lcw.one.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 日志Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
		Date beginDate  = DateUtils.setDays(new Date(), 1);
		Date endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		return "modules/sys/logList";
	}

}
