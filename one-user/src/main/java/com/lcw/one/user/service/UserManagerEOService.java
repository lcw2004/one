package com.lcw.one.user.service;

import com.lcw.one.sys.service.SysRoleEOService;
import com.lcw.one.sys.service.SysUserRoleEOService;
import com.lcw.one.user.bean.ManageUserQueryCondition;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.dao.UserManagerEODao;
import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;

import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.UUID;
import com.lcw.one.util.utils.cipher.password.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerEOService extends CrudService<UserManagerEODao, UserManagerEO, String> {

    @Autowired
    private SysRoleEOService sysRoleEOService;

    @Autowired
    private UserInfoEOService userInfoEOService;

    @Autowired
    private UserContactInfoEOService userContactInfoEOService;

    @Autowired
    private SysUserRoleEOService sysUserRoleEOService;

    public PageInfo<UserManagerEO> page(ManageUserQueryCondition userQueryCondition) {
        PageInfo<UserManagerEO> pageInfo = dao.page(userQueryCondition);
        for (UserManagerEO userManager : pageInfo.getList()) {
            userManager.setRoleNameList(sysRoleEOService.getSysRoleNameListByUserId(userManager.getUserId()));
        }
        return pageInfo;
    }

    @Override
    public UserManagerEO get(String id) {
        UserManagerEO user = super.get(id);
        user.setRoleIdList(sysRoleEOService.getSysRoleIdListByUserId(id));
        return user;
    }

    @Override
    public UserManagerEO save(UserManagerEO entity) {
        if (entity.getSysOffice() == null) {
            throw new OneBaseException("用户所属机构不能为空");
        }
        if(StringUtils.isEmpty(entity.getUserInfo().getAccount())) {
            throw new OneBaseException("用户账户不能为空");
        }
        if (userInfoEOService.isExistByAccount(entity.getUserInfo().getAccount())) {
            throw new OneBaseException("用户账户已经存在");
        }

        String userId = UUID.randomUserId();
        entity.setOfficeId(entity.getSysOffice().getId());
        entity.setUserId(userId);

        UserInfoEO userInfoEO = entity.getUserInfo();
        userInfoEO.setUserId(userId);
        userInfoEO.setType(UserInfoTypeEnum.MANAGER.getValue());
        userInfoEO.setPassword(PasswordUtils.encryptPassword(entity.getPassword()));

        UserContactInfoEO userContactInfoEO = userInfoEO.getUserContactInfo();
        userContactInfoEO.setUserId(userId);

        userInfoEOService.save(userInfoEO);
        userContactInfoEOService.save(userContactInfoEO);
        sysUserRoleEOService.saveSysUserRole(userId, entity.getRoleIdList());
        super.save(entity);

        return entity;
    }

    @Override
    public UserManagerEO update(UserManagerEO entity) {
        // 更新用户信息
        entity.getUserInfo().setPassword(entity.getPassword());
        userInfoEOService.updateUserInfoEO(entity.getUserInfo());
        super.update(entity);

        // 更新角色信息
        sysUserRoleEOService.deleteRoleWhereNotIn(entity.getUserId(), entity.getRoleIdList());
        sysUserRoleEOService.saveSysUserRole(entity.getUserId(), entity.getRoleIdList());

        return entity;
    }
}
