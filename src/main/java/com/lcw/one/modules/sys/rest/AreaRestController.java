package com.lcw.one.modules.sys.rest;

import com.lcw.one.modules.sys.entity.Area;
import com.lcw.one.modules.sys.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${restPath}/sys/area")
public class AreaRestController {


    @Autowired
    private AreaService areaService;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "application/json")
    public Area listAsTree() {
        return areaService.organizeMenuListAsMenuTree(areaService.get("1"), areaService.findAll());
    }

}
