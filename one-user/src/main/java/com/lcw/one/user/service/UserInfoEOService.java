package com.lcw.one.user.service;

import com.lcw.one.sys.service.SysRoleEOService;
import com.lcw.one.user.dao.UserInfoEODao;
import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.CrudService;

import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.cipher.password.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoEOService extends CrudService<UserInfoEODao, UserInfoEO, String> {

    @Autowired
    private SysRoleEOService sysRoleEOService;

    public UserInfoEO getUserByLoginName(String userName) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByAccount(userName);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    public UserInfoEO getUserInfoEOByOpenId(String openId) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByOpenId(openId);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    public UserInfoEO getUserInfoEOByMobile(String mobile) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByMobile(mobile);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    public UserInfoEO login(String account, String password, String weChatId) {
        UserInfoEO userInfoEO = getUserByLoginName(account);
        // 验证登录名密码
        if (userInfoEO == null) {
            logger.warn("用户{}不存在", account);
            throw new OneBaseException("您输入的帐号或密码有误");
        }
        if (!PasswordUtils.validatePassword(password, userInfoEO.getPassword())) {
            logger.warn("用户{}密码错误", account);
            throw new OneBaseException("您输入的帐号或密码有误");
        }

        // 验证微信ID
        if (StringUtils.isNotEmpty(weChatId)) {
            // 如果是微信登录

            if (StringUtils.isNotEmpty(userInfoEO.getUserContactInfo().getWechatId())) {
                // 账户已经绑定微信号，验证微信号是否是当前微信
                if (!userInfoEO.getUserContactInfo().getWechatId().equals(weChatId)) {
                    throw new OneBaseException("请用绑定了该账户的微信登录");
                }
            } else {
                // 账户未绑定微信号，则将用户微信绑定到当前账户
                userInfoEO.getUserContactInfo().setWechatId(weChatId);
                this.save(userInfoEO);
            }
        }
        return userInfoEO;
    }

    @Override
    public UserInfoEO get(String s) {
        UserInfoEO userInfoEO = super.get(s);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    private void fetchRoleIdList(UserInfoEO userInfoEO) {
        if (userInfoEO != null) {
            userInfoEO.setRoleIdList(sysRoleEOService.getSysRoleIdListByUserId(userInfoEO.getUserId()));
        }
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

    public void updatePassword(String userId, String newPassword) {
        UserInfoEO userInfoEO = dao.getOne(userId);
        userInfoEO.setPassword(PasswordUtils.encryptPassword(newPassword));
        dao.save(userInfoEO);
    }

    public UserInfoEO updateUserInfo(UserInfoEO userInfoEO) {
        UserInfoEO userInDb = this.get(userInfoEO.getUserId());
        userInDb.setName(userInfoEO.getName());
        userInDb.setIdentityNumber(userInfoEO.getIdentityNumber());

        UserContactInfoEO userContactInfoEO = userInDb.getUserContactInfo();
        if (userContactInfoEO == null) {
            userContactInfoEO = new UserContactInfoEO();
            userContactInfoEO.setUserId(userInfoEO.getUserId());
        }
        userContactInfoEO.setPhone(userInfoEO.getUserContactInfo().getPhone());
        userContactInfoEO.setMobile(userInfoEO.getUserContactInfo().getMobile());
        userContactInfoEO.setAddress(userInfoEO.getUserContactInfo().getAddress());
        userInDb.setUserContactInfo(userContactInfoEO);
        return this.save(userInDb);
    }


    /**
     * 修改用户信息，只修改用户填入的信息，其他信息不修改
     *
     * @param userInfoEO
     */
    public void updateUserInfoEO(UserInfoEO userInfoEO) {
        UserInfoEO userInfoEOInDb = get(userInfoEO.getUserId());
        if (StringUtils.isNotEmpty(userInfoEO.getPassword())) {
            // 如果修改的密码不为空，则更新密码
            userInfoEOInDb.setPassword(PasswordUtils.encryptPassword(userInfoEO.getPassword()));
        } else {
            // 如果修改的密码为空，则不修改密码
        }
        userInfoEOInDb.setName(userInfoEO.getName());
        userInfoEOInDb.setBirthday(userInfoEO.getBirthday());
        userInfoEOInDb.setGender(userInfoEO.getGender());
        userInfoEOInDb.setIdentityNumber(userInfoEO.getIdentityNumber());
        userInfoEOInDb.setUserContactInfo(userInfoEO.getUserContactInfo());
        dao.save(userInfoEOInDb);
    }

    public boolean isExistByAccount(String account) {
        return dao.isExistByAccount(account);
    }

    public void unbindWechat(String userId, String openId) {
        UserInfoEO userInfoEO = super.get(userId);
        // 验证微信ID与用户ID是否一致
        if (userInfoEO.getUserContactInfo() != null
                && StringUtils.isNotEmpty(userInfoEO.getUserContactInfo().getWechatId())
                && userInfoEO.getUserContactInfo().getWechatId().equals(openId)) {
            dao.unbindWechat(userId);
        } else {
            throw new OneBaseException("微信账户与用户未绑定");
        }
    }

    /**
     * 获取用户所有角色中的最大的数据权限
     * @param userId
     * @return
     */
    public Integer getMaxDataScope(String userId) {
        return dao.getMaxDataScope(userId);
    }
}
