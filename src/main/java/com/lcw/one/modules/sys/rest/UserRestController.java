package com.lcw.one.modules.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.web.ResponseMessage;
import com.lcw.one.modules.sys.entity.User;
import com.lcw.one.modules.sys.service.SystemService;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
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
    public Page<User> listAsTree(String name, HttpServletRequest request, HttpServletResponse response) {
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
        User userInDb = systemService.getUser(user.getId());
        user.setPassword(userInDb.getPassword());
        systemService.saveUser(user);
    }

    /**
     * 查看当前登录用户的信息
     * @return
     */
    @RequiresUser
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
    public User currentUserInfo() {
        return UserUtils.getUser();
    }

    /**
     * 修改当前登录用户的信息
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
        return ResponseMessage.SUCCESS;
    }
}
