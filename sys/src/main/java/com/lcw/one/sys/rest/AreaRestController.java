package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.Area;
import com.lcw.one.sys.service.AreaService;
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
        return areaService.organizeMenuListAsMenuTree(areaService.get("1"), areaService.queryAll());
    }

    @RequiresPermissions("sys:area:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Area get(@PathVariable String id) {
        Area area = areaService.get(id);
        area.setParent(areaService.get(area.getParentId()));
        return area;
    }

    @RequiresPermissions("sys:area:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        areaService.delete(id);
    }

    @RequiresPermissions("sys:area:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody Area area) {
        area.setParentId(area.getParent().getId());
        areaService.save(area);
    }

}
