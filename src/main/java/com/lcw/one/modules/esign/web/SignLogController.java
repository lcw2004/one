/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.esign.web;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.web.BaseController;
import com.lcw.one.modules.esign.entity.SealSignLog;
import com.lcw.one.modules.esign.service.SealSignLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/esign/signlog")
public class SignLogController extends BaseController {

	@Autowired
	private SealSignLogService sealSignLogService;

	@RequiresPermissions("esign:signlog:view")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, Model model) {
 		Page<SealSignLog> page = sealSignLogService.find(new Page<SealSignLog>(request, response), paramMap);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "modules/esign/sealSignLog/list";
	}

	@RequiresPermissions("esign:signlog:view")
	@RequestMapping(value = "detail")
	public String detail(String id, Model model) {
		SealSignLog sealSignLog = sealSignLogService.get(id);
		model.addAttribute("sealSignLog", sealSignLog);
		return "modules/esign/sealSignLog/detail";
	}

}
