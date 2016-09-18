package com.lcw.one.modules.sys.rest;

import com.lcw.one.modules.sys.entity.Area;
import com.lcw.one.modules.sys.entity.Menu;
import com.lcw.one.modules.sys.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${restPath}/sys/area")
public class AreaRestController {

    @Autowired
    private AreaService areaService;

    @RequiresPermissions("sys:area:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "application/json")
    public Area listAsTree() {
        return areaService.organizeMenuListAsMenuTree(areaService.get("1"), areaService.findAll());
    }

    @RequiresPermissions("sys:area:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Area get(@PathVariable String id) {
        Area menu = areaService.get(id);
        return menu;
    }

    @RequiresPermissions("sys:area:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        areaService.delete(id);
    }

    @RequiresPermissions("sys:area:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody Area area) {
        areaService.save(area);
    }

}
