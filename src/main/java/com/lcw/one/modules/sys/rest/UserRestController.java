package com.lcw.one.modules.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.modules.sys.entity.User;
import com.lcw.one.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "${restPath}/sys/user")
public class UserRestController {

    @Autowired
    private SystemService systemService;

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<User> listAsTree(User user, HttpServletRequest request, HttpServletResponse response) {
        Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        for (User userDb: page.getList()) {
            userDb.getCompany();
            userDb.getOffice();
        }
        return page;
    }

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User get(@PathVariable String id) {
        User user = systemService.getUser(id);
        return user;
    }

    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        systemService.deleteUser(id);
    }

    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody User user) {
        systemService.saveUser(user);
    }

}
