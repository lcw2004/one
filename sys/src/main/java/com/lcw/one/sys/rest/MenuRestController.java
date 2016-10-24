package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.Menu;
import com.lcw.one.sys.service.MenuServices;
import com.lcw.one.sys.service.SystemService;
import com.lcw.one.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/sys/menu")
public class MenuRestController {

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

    /**
     * 首页，根据用户获取用户的权限列表树
     * @return
     */
    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public Menu listUserPermisions() {
        return UserUtils.getMenu();
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Menu get(@PathVariable String id) {
        Menu menu = systemService.getMenu(id);
        // TODO 忽略不必要的属性
        return menu;
    }

    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        systemService.deleteMenu(id);
    }


    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody Menu menu) {
        systemService.saveMenu(menu);
    }
}
