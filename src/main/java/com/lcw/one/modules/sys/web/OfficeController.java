/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lcw.one.common.config.Global;
import com.lcw.one.common.utils.StringUtils;
import com.lcw.one.common.web.BaseController;
import com.lcw.one.modules.sys.entity.Office;
import com.lcw.one.modules.sys.entity.User;
import com.lcw.one.modules.sys.service.OfficeService;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 机构Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return officeService.get(id);
		} else {
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping({"list", ""})
	public String list(Office office, Model model) {
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping("form")
	public String form(String id, Model model) {
		model.addAttribute("id", id);
		return "modules/sys/officeForm";
	}
	
}
