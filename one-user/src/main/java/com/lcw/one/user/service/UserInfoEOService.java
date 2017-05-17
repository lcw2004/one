package com.lcw.one.user.service;

import com.lcw.one.sys.entity.SysUserRoleEO;
import com.lcw.one.user.dao.UserInfoEODao;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.PasswordUtils;
import org.springframework.stereotype.Service;

@Service
public class UserInfoEOService extends CrudService<UserInfoEODao, UserInfoEO> {

    public UserInfoEO getUserByLoginName(String userName) {
        return dao.getUserInfoEOByAccount(userName);
    }

    public void updateUserLoginInfo(String userId, String loginIp) {
        dao.updateUserLoginInfo(userId, loginIp);
    }

    public void updatePassword(String userId, String oldPassword, String newPassword) {
        UserInfoEO userInfoEO = dao.getOne(userId);
        if (!PasswordUtils.validatePassword(oldPassword, userInfoEO.getPassword())) {
            throw new OneBaseException("0001", "修改密码失败，旧密码错误");
        }

        userInfoEO.setPassword(PasswordUtils.encryptPassword(newPassword));
        dao.save(userInfoEO);
    }

    public void updateUserInfoEO(UserInfoEO userInfoEO) {
        UserInfoEO userInfoEOInDb = get(userInfoEO.getUserId());
        userInfoEOInDb.setName(userInfoEO.getName());
        userInfoEOInDb.setBirthday(userInfoEO.getBirthday());
        userInfoEOInDb.setGender(userInfoEO.getGender());
        userInfoEOInDb.setIdentityNumber(userInfoEO.getIdentityNumber());
        userInfoEOInDb.setUserContactInfo(userInfoEO.getUserContactInfo());
        dao.save(userInfoEOInDb);
    }
}
