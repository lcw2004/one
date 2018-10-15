package com.lcw.one.user.service;

import com.lcw.one.base.config.GlobalConfig;
import com.lcw.one.sys.service.SysRoleEOService;
import com.lcw.one.user.constant.UserInfoStatusEnum;
import com.lcw.one.user.dao.UserInfoEODao;
import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.cipher.password.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoEOService extends CrudService<UserInfoEODao, UserInfoEO, String> {

    @Autowired
    private SysRoleEOService sysRoleEOService;

    @Override
    public UserInfoEO get(String s) {
        UserInfoEO userInfoEO = super.get(s);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    @Override
    public void delete(String s) {
        this.updateStatus(s, UserInfoStatusEnum.DELETE.getValue());
    }

    private void fetchRoleIdList(UserInfoEO userInfoEO) {
        if (userInfoEO != null) {
            userInfoEO.setRoleIdList(sysRoleEOService.getSysRoleIdListByUserId(userInfoEO.getUserId()));
        }
    }

    /**
     * 通过登录账户查询用户
     *
     * @param userName 登录账户
     * @return
     */
    public UserInfoEO getUserByLoginName(String userName) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByAccount(userName);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    public UserInfoEO getUserByLoginName(Integer userType, String userName) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByAccount(userType, userName);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    /**
     * 通过微信openId查找用户
     *
     * @param openId 微信OpenId
     * @return
     */
    public UserInfoEO getUserInfoEOByOpenId(String openId) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByOpenId(openId);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    /**
     * 通过手机号查询用户
     *
     * @param mobile 手机号
     * @return
     */
    public UserInfoEO getUserInfoEOByMobile(String mobile) {
        UserInfoEO userInfoEO = dao.getUserInfoEOByMobile(mobile);
        fetchRoleIdList(userInfoEO);
        return userInfoEO;
    }

    /**
     * 通过登录名列表获取用户
     *
     * @param accountList
     * @return
     */
    public List<UserInfoEO> listByAccount(List<String> accountList, Integer userType) {
        return dao.listByAccount(accountList, userType);
    }

    /**
     * 判断账户是否存在
     *
     * @param userType
     * @param account
     * @param excludeUserId
     * @return
     */
    public boolean isExistByAccount(Integer userType, String account, String excludeUserId) {
        return dao.isExistByAccount(userType, account, excludeUserId);
    }


    /**
     * 验证登录账户
     * @param account
     * @param excludeUserId
     */
    public void validAccount(Integer userType, String account, String excludeUserId) {
        if (isExistByAccount(userType, account, excludeUserId)) {
            throw new OneBaseException("0002", "该账户已经注册过了");
        }
    }

    /**
     * 判断手机号码是否存在
     *
     * @param mobile
     * @param excludeUserId
     * @return
     */
    public boolean isExistByMobile(String mobile, String excludeUserId) {
        return dao.isExistByMobile(mobile, excludeUserId);
    }

    /**
     * 验证用户信息
     * 1. 验证账户是否存在
     * 2. 验证手机号码是否存在
     *
     * @param userInfoEO
     */
    public void validateUserInfo(UserInfoEO userInfoEO) {
        if (StringUtils.isEmpty(userInfoEO.getAccount())) {
            throw new OneBaseException("用户账户不能为空");
        }
        if (isExistByAccount(userInfoEO.getType(), userInfoEO.getAccount(), userInfoEO.getUserId())) {
            throw new OneBaseException("用户账户[" + userInfoEO.getAccount() + "]已经存在");
        }
//        if (StringUtils.isNotEmpty(userInfoEO.getUserContactInfo().getMobile())) {
//            if (isExistByMobile(userInfoEO.getUserContactInfo().getMobile(), userInfoEO.getUserId())) {
//                throw new OneBaseException("用户手机号码[" + userInfoEO.getUserContactInfo().getMobile() + "]已经存在");
//            }
//        }
    }

    /**
     * 通过微信登录，登录成功之后绑定微信好
     * @param account
     * @param password
     * @param weChatId
     * @return
     */
    public UserInfoEO loginAndBindWeChatId(String account, String password, String weChatId) {
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


    /**
     * 改用户状态
     * @param userId
     * @param newStatus
     */
    public void updateStatus(String userId, Integer newStatus) {
        dao.updateStatus(userId, newStatus);
    }

    /**
     * 修改用户登录IP
     * @param userId
     * @param loginIp 登录IP
     */
    public void updateLoginIp(String userId, String loginIp) {
        dao.updateLoginIp(userId, loginIp);
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
    public void updatePassword(String userId, String oldPassword, String newPassword) {
        UserInfoEO userInfoEO = dao.getOne(userId);
        if (!PasswordUtils.validatePassword(oldPassword, userInfoEO.getPassword())) {
            throw new OneBaseException("0001", "修改密码失败，旧密码错误");
        }

        this.updatePassword(userId, newPassword);
    }

    /**
     * 重置用户密码为默认密码
     *
     * @param userId 用户ID
     */
    public void resetPassword(String userId) {
        this.updatePassword(userId, GlobalConfig.getValue("system_default_password"));
    }

    /**
     * 修改用户密码（对明文密码加密）
     *
     * @param userId        用户ID
     * @param plainPassword 明文密码
     */
    public void updatePassword(String userId, String plainPassword) {
        dao.updatePassword(userId, PasswordUtils.encryptPassword(plainPassword));
    }

    /**
     * 修改用户信息（用户自己修改）
     * @param userInfoEO
     * @return
     */
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
        userContactInfoEO.setEmail(userInfoEO.getUserContactInfo().getEmail());
        userInDb.setUserContactInfo(userContactInfoEO);
        return this.save(userInDb);
    }

    /**
     * 修改用户信息，只修改用户填入的信息，其他信息不修改（管理员修改）
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
        userInfoEOInDb.setAccount(userInfoEO.getAccount());
        userInfoEOInDb.setName(userInfoEO.getName());
        userInfoEOInDb.setBirthday(userInfoEO.getBirthday());
        userInfoEOInDb.setGender(userInfoEO.getGender());
        userInfoEOInDb.setIdentityNumber(userInfoEO.getIdentityNumber());
        userInfoEOInDb.setUserContactInfo(userInfoEO.getUserContactInfo());
        dao.save(userInfoEOInDb);
    }

    /**
     * 解绑微信ID
     * @param userId
     * @param openId
     */
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
     *
     * @param userId
     * @return
     */
    public Integer getMaxDataScope(String userId) {
        return dao.getMaxDataScope(userId);
    }
}
