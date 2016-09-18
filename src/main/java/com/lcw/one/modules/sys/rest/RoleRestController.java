package com.lcw.one.modules.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.modules.sys.entity.Role;
import com.lcw.one.modules.sys.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "${restPath}/sys/role")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("sys:role:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page list(HttpServletRequest request, HttpServletResponse response) {
        Page<Role> page = roleService.find(new Page<Role>(request, response));
        return page;
    }

    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public void update(@RequestBody Role role) {
        roleService.save(role);
    }

    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void add(@RequestBody Role role) {
        roleService.save(role);
    }

    @RequiresPermissions("sys:role:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Role get(@PathVariable String id) {
        Role role = roleService.get(id);
        role.setUserList(null);
        role.setMenuList(null);
        role.setUserList(null);
        role.getOffice();
        role.getOfficeList();
        return role;
    }

    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        roleService.delete(id);
    }

}
