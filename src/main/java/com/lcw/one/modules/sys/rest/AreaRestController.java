package com.lcw.one.modules.sys.rest;

import com.lcw.one.modules.sys.entity.Menu;
import com.lcw.one.modules.sys.service.MenuServices;
import com.lcw.one.modules.sys.service.SystemService;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/sys/area")
public class AreaRestController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private MenuServices menuServices;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Menu> list() {
        return menuServices.findAllList();
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "application/json")
    public Menu listAsTree() {
        return menuServices.organizeMenuListAsMenuTree(menuServices.get("1"), list());
    }

}
