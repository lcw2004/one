package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.Office;
import com.lcw.one.sys.service.OfficeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${restPath}/sys/office")
public class OfficeRestController {

    @Autowired
    private OfficeService officeService;

    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "application/json")
    public Office listAsTree() {
        return officeService.organizeMenuListAsMenuTree(officeService.get("1"), officeService.findAll());
    }

    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Office get(@PathVariable String id) {
        Office menu = officeService.get(id);
        return menu;
    }

    @RequiresPermissions("sys:office:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        officeService.delete(id);
    }

    @RequiresPermissions("sys:office:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody Office area) {
        officeService.save(area);
    }

}
