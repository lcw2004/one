package com.lcw.one.user.service;

import com.lcw.one.baseInfo.dao.BasePurchaseTypeEODao;
import com.lcw.one.baseInfo.dao.BaseQualificationTypeEODao;
import com.lcw.one.baseInfo.service.BasePurchaseTypeEOService;
import com.lcw.one.sys.constant.SysDefaultRoleEnum;
import com.lcw.one.sys.entity.SysUserRoleEO;
import com.lcw.one.sys.service.SysUserRoleEOService;
import com.lcw.one.user.bean.SupplierQueryCondition;
import com.lcw.one.user.bean.vo.SupplierUserRegistryVO;
import com.lcw.one.user.constant.AuditStatusEnum;
import com.lcw.one.user.constant.UserInfoStatusEnum;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.constant.UserSupplierStatusEnum;
import com.lcw.one.user.dao.UserSupplierEODao;
import com.lcw.one.user.dao.UserSupplierPurchaseTypeRelationEODao;
import com.lcw.one.user.entity.*;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.PasswordUtils;
import com.lcw.one.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserSupplierEOService extends CrudService<UserSupplierEODao, UserSupplierEO> {

    @Autowired
    private UserInfoEOService userInfoEOService;

    @Autowired
    private SysUserRoleEOService sysUserRoleEOService;

    @Autowired
    private UserContactInfoEOService userContactInfoEOService;

    @Autowired
    private UserSupplierUserEOService userSupplierUserEOService;

    @Autowired
    private UserSupplierPurchaseTypeRelationEOService userSupplierPurchaseTypeRelationEOService;

    @Autowired
    private UserSupplierPurchaseTypeRelationEODao userSupplierPurchaseTypeRelationEODao;

    @Autowired
    private UserSupplierQualificationEOService userSupplierQualificationEOService;

    public PageInfo<UserSupplierEO> page(PageInfo pageInfo, SupplierQueryCondition queryCondition) {
        return dao.page(pageInfo, queryCondition);
    }

    @Override
    public UserSupplierEO get(String id) {
        UserSupplierEO userSupplierEO = dao.findOne(id);

        // 负责人信息
        UserSupplierUserEO userSupplierUserEO = userSupplierUserEOService.get(userSupplierEO.getSupplierId(), userSupplierEO.getPrincipalUser().getUserId());
        userSupplierEO.setPrincipalUserInfo(userSupplierUserEO);

        // 查找投标类别
        List<UserSupplierPurchaseTypeRelationEO> purchaseTypeRelationEOList = userSupplierPurchaseTypeRelationEODao.listBySupplierId(userSupplierEO.getSupplierId());
        userSupplierEO.setPurchaseTypeList(purchaseTypeRelationEOList);

        // 查询资质文件
        List<UserSupplierQualificationEO> qualificationEOList = userSupplierQualificationEOService.listBySupplierId(id);
        userSupplierEO.setQualificationList(qualificationEOList);
        return userSupplierEO;
    }

    @Override
    public void delete(String id) {
        dao.updateSupplierState(id, UserSupplierStatusEnum.DELETED.getValue());
    }

    public void start(String userId) {
        dao.updateSupplierState(userId, UserSupplierStatusEnum.NORMAL.getValue());
    }

    public void stop(String userId) {
        dao.updateSupplierState(userId, UserSupplierStatusEnum.STOPED.getValue());
    }

    public void moveToBlacklist(String userId) {
        // TODO 黑名单单独拿一个表出来
        dao.updateSupplierState(userId, UserSupplierStatusEnum.BLACKLIST.getValue());
    }

    public void moveFromBlacklist(String userId) {
        // TODO 黑名单单独拿一个表出来
        dao.updateSupplierState(userId, UserSupplierStatusEnum.NORMAL.getValue());
    }

    public UserSupplierEO getUserSupplierEOByName(String name) {
        return dao.getUserSupplierEOByName(name);
    }

    public UserSupplierEO getUserSupplierEOByPrincipalUserUserId(String userId) {
        UserSupplierEO userSupplierEO = dao.getUserSupplierEOByPrincipalUserUserId(userId);
        return get(userSupplierEO.getSupplierId());
    }

    /**
     * 完善供应商信息，该接口有如下三个地方调用
     * 1.   供应商完善自己的信息
     * 2.   供应商修改自己的信息
     * 3.   管理员修改供应商的信息
     *
     * @param userSupplierEO
     * @return
     */
    @Transactional
    public UserSupplierEO prefectSupplier(UserSupplierEO userSupplierEO) {
        userSupplierEO.setStatus(UserSupplierStatusEnum.NORMAL.getValue());
        userSupplierUserEOService.save(userSupplierEO.getPrincipalUserInfo());
        userInfoEOService.updateUserInfoEO(userSupplierEO.getPrincipalUser());
        super.update(userSupplierEO);
        userSupplierPurchaseTypeRelationEOService.deletePurchaseTypeBySupplierIdNotInList(userSupplierEO.getSupplierId(), userSupplierEO.getPurchaseTypeList());
        if (CollectionUtils.isNotEmpty(userSupplierEO.getPurchaseTypeList())) {
            for (UserSupplierPurchaseTypeRelationEO relationEO : userSupplierEO.getPurchaseTypeList()) {
                relationEO.setSupplierId(userSupplierEO.getSupplierId());

                // 如果字段为空表示是新添加的数据，需要审核通过
                if(relationEO.getStatus() == null) {
                    relationEO.setStatus(AuditStatusEnum.AUDIT.getValue());
                }

                userSupplierPurchaseTypeRelationEODao.save(relationEO);
            }
        }

        // 启动工作流
//        String supplierUserId = userSupplierEO.getPrincipalUser().getUserId();
//        String supplierName = userSupplierEO.getName();
//        flowRestController.startWorkflow(WorkflowDefEnum.SupplierPurchaseTypeApply.getFlowId(), userSupplierEO.getSupplierId(), supplierUserId, supplierName, null);
        return userSupplierEO;
    }

    /**
     * 注册供应商用户
     *
     * @param registry
     * @param clientIp
     * @return
     */
    public UserSupplierEO registrySupplierUserInfo(SupplierUserRegistryVO registry, String clientIp) {
        String userId = UUID.randomUserId();

        UserContactInfoEO userContactInfoEO = new UserContactInfoEO();
        userContactInfoEO.setUserId(userId);
        userContactInfoEO.setEmail(registry.getEmail());
        userContactInfoEO.setMobile(registry.getMobile());
        userContactInfoEO.setPhone(registry.getPhone());
        userContactInfoEOService.save(userContactInfoEO);

        UserInfoEO userInfoEO = new UserInfoEO();
        userInfoEO.setName(registry.getUserName());
        userInfoEO.setUserId(userId);
        userInfoEO.setUserContactInfo(userContactInfoEO);
        userInfoEO.setAccount(registry.getAccount());
        userInfoEO.setType(UserInfoTypeEnum.SUPPLIER.getValue());
        userInfoEO.setStatus(UserInfoStatusEnum.NORMAL.getValue());
        userInfoEO.setPassword(PasswordUtils.encryptPassword(registry.getPassword()));
        userInfoEO.setCreateIp(clientIp);
        userInfoEO.setCreateTime(new Date());
        userInfoEOService.save(userInfoEO);

        SysUserRoleEO sysUserRoleEO = new SysUserRoleEO();
        sysUserRoleEO.setRoleId(SysDefaultRoleEnum.SUPPLIER_ROLE.getRoleId());
        sysUserRoleEO.setUserId(userId);
        sysUserRoleEOService.save(sysUserRoleEO);

        UserSupplierEO supplierEO = new UserSupplierEO();
        supplierEO.setSupplierId(UUID.randomUserId());
        supplierEO.setPrincipalUser(userInfoEO);
        supplierEO.setStatus(UserSupplierStatusEnum.NOT_COMMIT.getValue());
        supplierEO.setName(registry.getSupplierName());
        supplierEO.setCreateTime(new Date());
        dao.save(supplierEO);

        UserSupplierUserEO userSupplierUserEO = new UserSupplierUserEO();
        userSupplierUserEO.setUserId(userId);
        userSupplierUserEO.setSupplierId(supplierEO.getSupplierId());
        userSupplierUserEOService.save(userSupplierUserEO);

        return supplierEO;
    }

}
