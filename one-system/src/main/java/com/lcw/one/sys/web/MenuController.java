/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lcw.one.common.web.BaseController;
import com.lcw.one.sys.entity.Menu;
import com.lcw.one.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 菜单Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private SystemService systemService;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = {"list", ""})
    public String list(Model model) {
        return "modules/sys/menuList";
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "form")
    public String form(String id, Model model) {
        model.addAttribute("id", id);
        return "modules/sys/menuForm";
    }

    @RequiresUser
    @RequestMapping(value = "tree")
    public String tree() {
        return "modules/sys/menuTree";
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) Long extId, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Menu> list = systemService.findAllMenu();
        for (int i = 0; i < list.size(); i++) {
            Menu e = list.get(i);
            if (extId == null || (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1)) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParent() != null ? e.getParent().getId() : 0);
                map.put("name", e.getName());
                mapList.add(map);
            }
        }
        return mapList;
    }
}
