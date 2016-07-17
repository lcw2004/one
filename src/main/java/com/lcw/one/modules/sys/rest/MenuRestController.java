package com.lcw.one.modules.sys.rest;

import com.google.common.collect.Lists;
import com.lcw.one.modules.sys.entity.Menu;
import com.lcw.one.modules.sys.service.SystemService;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/sys/menu")
public class MenuRestController {

    @Autowired
    private SystemService systemService;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Menu> list() {
        List<Menu> list = Lists.newArrayList();
        List<Menu> sourcelist = systemService.findAllMenu();
        Menu.sortList(list, sourcelist, "1");
        return list;
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public Menu listByUser() {
        return UserUtils.getMenu();
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Menu getMenuById(@PathVariable String id) {
        Menu menu = systemService.getMenu(id);
        return menu;
    }

}
