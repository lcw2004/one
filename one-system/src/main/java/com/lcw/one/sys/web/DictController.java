/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.web;

import com.lcw.one.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 字典Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = {"list", ""})
    public String list() {
        return "modules/sys/dictList";
    }

    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "form")
    public String form(String id, Model model) {
        model.addAttribute("id", id);
        return "modules/sys/dictForm";
    }

}
