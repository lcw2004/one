package com.lcw.one.sys.rest;

import com.google.common.collect.Lists;
import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.web.ResponseMessage;
import com.lcw.one.sys.entity.Role;
import com.lcw.one.sys.entity.User;
import com.lcw.one.sys.service.SystemService;
import com.lcw.one.sys.service.UserService;
import com.lcw.one.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/sys/user")
public class UserRestController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private UserService userService;

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<User> list(String name, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setName(name);
        Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        for (User userDb : page.getList()) {
            userDb.getCompany();
            userDb.getOffice();
        }
        return page;
    }

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User get(@PathVariable String id) {
        User user = systemService.getUser(id);

        List<String> roleIdList = Lists.newArrayList();
        for (Role role : user.getRoleList()) {
            roleIdList.add(role.getId());
        }
        user.setRoleIdList(roleIdList);

        return user;
    }

    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        systemService.deleteUser(id);
    }

    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public void update(@RequestBody User user) {
        // 保存用户基础信息
        User userInDb = userService.get(user.getId());
        user.setPassword(userInDb.getPassword());

        // 保存用户角色信息
        List<Role> roleList = Lists.newArrayList();
        List<String> roleIdList = user.getRoleIdList();
        for (Role r : systemService.findAllRole()) {
            if (roleIdList.contains(r.getId())) {
                roleList.add(r);
            }
        }
        user.setRoleList(roleList);

        userService.save(user);
    }

    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody User user) {
        user.setPassword(SystemService.entryptPassword("123456"));

        // 保存用户角色信息
        List<Role> roleList = Lists.newArrayList();
        List<String> roleIdList = user.getRoleIdList();
        for (Role r : systemService.findAllRole()) {
            if (roleIdList.contains(r.getId())) {
                roleList.add(r);
            }
        }
        user.setRoleList(roleList);

        userService.save(user);
    }

    /**
     * 查看当前登录用户的信息
     *
     * @return
     */
    @RequiresUser
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
    public User currentUserInfo() {
        return UserUtils.getUser();
    }

    /**
     * 修改当前登录用户的信息
     *
     * @return
     */
    @RequiresUser
    @RequestMapping(value = "info", method = RequestMethod.PUT, produces = "application/json")
    public ResponseMessage updateCurrentUserInfo(@RequestBody User user) {
        if (StringUtils.isBlank(user.getName())) {
            return new ResponseMessage("0001", "姓名不能为空");
        }

        User currentUser = UserUtils.getUser(true);
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        currentUser.setMobile(user.getMobile());
        currentUser.setRemarks(user.getRemarks());
        systemService.saveUser(currentUser);
        return new ResponseMessage("修改成功");
    }

    /**
     * 修改当前登录用户的密码
     *
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     * @return
     */
    @RequiresUser
    @RequestMapping(value = "password", method = RequestMethod.PUT, produces = "application/json")
    public ResponseMessage updatePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(confirmNewPassword)) {
            return new ResponseMessage("0001", "密码不能为空");
        }

        User user = UserUtils.getUser();
        if (!SystemService.validatePassword(oldPassword, user.getPassword())) {
            return new ResponseMessage("0002", "修改密码失败，旧密码错误");
        }

        systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
        UserUtils.logout();
        return new ResponseMessage("修改密码成功，请重新登录");
    }
}
