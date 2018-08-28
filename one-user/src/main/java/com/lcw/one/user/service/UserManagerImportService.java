package com.lcw.one.user.service;

import com.lcw.one.base.config.GlobalConfig;
import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.sys.service.SysOfficeEOService;
import com.lcw.one.sys.service.SysUserRoleEOService;
import com.lcw.one.user.bean.vo.ExcelImportBean;
import com.lcw.one.user.bean.vo.ExcelImportFailDataBean;
import com.lcw.one.user.bean.vo.ManageUserVO;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.excel.rule.ExcelClassRule;
import com.lcw.one.util.excel.rule.ExcelFieldRule;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.Exceptions;
import com.lcw.one.util.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManagerImportService extends UserImportService<ManageUserVO> {

    private static final Logger logger = LoggerFactory.getLogger(UserManagerImportService.class);

    @Autowired
    private UserManagerEOService userManagerEOService;

    @Autowired
    private UserInfoEOService userInfoEOService;

    @Autowired
    private SysOfficeEOService sysOfficeEOService;

    @Autowired
    private SysUserRoleEOService sysUserRoleEOService;

    @Override
    Integer getUserType() {
        return UserInfoTypeEnum.MANAGER.getValue();
    }

    @Override
    public void update(ManageUserVO manageUser, UserInfoEO userInfoInDB, ExcelImportBean<ManageUserVO> importResult) {
        try {
            userInfoInDB.getUserContactInfo().setMobile(manageUser.getMobile());
            userInfoInDB.getUserContactInfo().setPhone(manageUser.getPhone());
            userInfoInDB.getUserContactInfo().setEmail(manageUser.getEmail());
            userInfoInDB.setName(manageUser.getName());
            userInfoEOService.save(userInfoInDB);

            UserManagerEO userManagerEO = userManagerEOService.get(userInfoInDB.getUserId());
            if (userManagerEO == null) {
                throw new OneBaseException("系统存在同名的用户，ID：" + userInfoInDB.getUserId());
            }
            userManagerEO.setRemark(manageUser.getRemark());
            userManagerEO.setSysOffice(manageUser.getSysOffice());

            // 保存角色
            if (importResult.getParamMap() != null && importResult.getParamMap().containsKey("roleIdList")) {
                List<String> roleIdList = (List<String>) importResult.getParamMap().get("roleIdList");
                sysUserRoleEOService.saveSysUserRole(userInfoInDB.getUserId(), roleIdList);
            }

            importResult.increaseSuccessCount();
        } catch (OneBaseException e) {
            importResult.getFailDataList().add(new ExcelImportFailDataBean<>(e.getMessage(), manageUser));
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            importResult.getFailDataList().add(new ExcelImportFailDataBean<>(Exceptions.getMessage(e), manageUser));
        }
    }

    @Override
    public void save(ManageUserVO manageUser, ExcelImportBean<ManageUserVO> importResult) {
        try {
            UserContactInfoEO userContactInfoEO = new UserContactInfoEO();
            userContactInfoEO.setMobile(manageUser.getMobile());
            userContactInfoEO.setPhone(manageUser.getPhone());
            userContactInfoEO.setEmail(manageUser.getEmail());

            UserInfoEO userInfoEO = new UserInfoEO();
            userInfoEO.setAccount(manageUser.getAccount());
            userInfoEO.setName(manageUser.getName());
            userInfoEO.setUserContactInfo(userContactInfoEO);

            UserManagerEO userManagerEO = new UserManagerEO();
            userManagerEO.setPassword(GlobalConfig.getValue("system_default_password"));
            userManagerEO.setUserInfo(userInfoEO);
            userManagerEO.setSysOffice(manageUser.getSysOffice());
            userManagerEO.setRemark(manageUser.getRemark());

            userManagerEOService.save(userManagerEO);

            // 保存角色
            if (importResult.getParamMap() != null && importResult.getParamMap().containsKey("roleIdList")) {
                List<String> roleIdList = (List<String>) importResult.getParamMap().get("roleIdList");
                sysUserRoleEOService.saveSysUserRole(userManagerEO.getUserId(), roleIdList);
            }

            importResult.increaseSuccessCount();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            importResult.getFailDataList().add(new ExcelImportFailDataBean<>(Exceptions.getMessage(e), manageUser));
        }
    }

    @Override
    public void prepareData(List<ManageUserVO> excelDataList, ExcelImportBean<ManageUserVO> importResult) {
        // 获取所有的所有机构，并验证是否匹配
        List<String> officeNameList = ObjectUtils.getFieldValueList(excelDataList, "officeName");
        List<SysOfficeEO> officeList = sysOfficeEOService.listByName(officeNameList);
        Map<String, SysOfficeEO> officeMap = ObjectUtils.asMapByFiled(officeList, "name");

        for (Iterator<ManageUserVO> it = excelDataList.iterator(); it.hasNext(); ) {
            ManageUserVO manageUser = it.next();
            if (officeMap != null && officeMap.containsKey(manageUser.getOfficeName())) {
                manageUser.setSysOffice(officeMap.get(manageUser.getOfficeName()));
            } else {
                it.remove();
                importResult.getFailDataList().add(new ExcelImportFailDataBean<>("所属机构/单位[" + manageUser.getOfficeName() + "]不存在", manageUser));
            }
        }
    }

    public static List<ManageUserVO> templateData() {
        ManageUserVO manageUser = new ManageUserVO();
        manageUser.setAccount("登录账户");
        manageUser.setName("姓名");
        manageUser.setEmail("bairui@163.com");
        manageUser.setPhone("010-11111111");
        manageUser.setMobile("13800000000");
        return Collections.singletonList(manageUser);
    }

    public static ExcelClassRule buildExcelRule() {
        ExcelClassRule excelClassRule = new ExcelClassRule();
        excelClassRule.setOrderField("index");
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("officeName", "归属单位/部门", false, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("account", "登录账号", false, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("name", "姓名", false, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("phone", "电话", true, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("mobile", "手机", true, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("email", "邮箱", true, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("remark", "备注", true, String.class));
        return excelClassRule;
    }

}
