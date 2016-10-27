package com.lcw.one.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.sys.entity.Role;
import com.lcw.one.sys.rest.bean.BeanUtils;
import com.lcw.one.sys.rest.bean.Role4Rest;
import com.lcw.one.sys.service.RoleService;
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
    public Page list(String name, HttpServletRequest request, HttpServletResponse response) {
        Page page = roleService.find(new Page<Role>(request, response), name);
        page.setList(BeanUtils.to(page.getList()));
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
    public Role4Rest get(@PathVariable String id) {
        Role role = roleService.get(id);
        role.getOffice();
        role.getMenuIdList();

        Role4Rest role4Rest = new Role4Rest(role);
        role4Rest.setOffice(role.getOffice());
        role4Rest.setMenuIdList(role.getMenuIdList());
        return role4Rest;
    }

    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        roleService.delete(id);
    }

}
