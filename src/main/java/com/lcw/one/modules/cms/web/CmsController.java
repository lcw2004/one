/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.cms.web;

import com.lcw.one.common.web.BaseController;
import com.lcw.one.modules.cms.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 内容管理Controller
 * @author ThinkGem
 * @version 2013-4-21
 */
@Controller
@RequestMapping(value = "${adminPath}/cms")
public class CmsController extends BaseController {

	@Autowired
	private CategoryService categoryService;
	
	@RequiresPermissions("cms:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/cms/cmsIndex";
	}
	
	@RequiresPermissions("cms:view")
	@RequestMapping(value = "tree")
	public String tree(Model model) {
		model.addAttribute("categoryList", categoryService.findByUser(true, null));
		return "modules/cms/cmsTree";
	}
	
	@RequiresPermissions("cms:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/cms/cmsNone";
	}

}
