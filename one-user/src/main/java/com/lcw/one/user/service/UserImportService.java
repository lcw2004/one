package com.lcw.one.user.service;

import com.lcw.one.user.bean.vo.*;
import com.lcw.one.user.bean.vo.UserImportVO;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public abstract class UserImportService<T extends UserImportVO> {

    @Autowired
    private UserInfoEOService userInfoEOService;

    /**
     * 获取用户类型
     *
     * @return
     */
    abstract Integer getUserType();

    /**
     * 更新数据
     */
    abstract void update(T manageUser, UserInfoEO userInfoInDB, ExcelImportBean<T> importResult);

    /**
     * 新建数据
     */
    abstract void save(T manageUser, ExcelImportBean<T> importResult);

    /**
     * 准备数据（可以检查数据是否异常）
     */
    abstract void prepareData(List<T> excelDataList, ExcelImportBean<T> importResult);

    /**
     * 导入用户
     *
     * @param coverType       重复数据覆盖类型
     * @param excelImportBean Excel数据
     * @return
     */
    public void importUser(Integer coverType, ExcelImportBean<T> excelImportBean) {
        // 导入数据
        if (coverType == 1) {
            importAndCoverRepeatData(excelImportBean);
        } else if (coverType == 2) {
            importAndStopIfHasRepeatData(excelImportBean);
        } else {
            importAndIgnoreRepeatData(excelImportBean);
        }

        // 对返回的数据按排序号进行排序
        excelImportBean.sortFailData();
    }

    /**
     * 覆盖导入重复数据
     */
    private void importAndCoverRepeatData(ExcelImportBean<T> excelImportBean) {
        List<T> sourceDataList = excelImportBean.getSourceDataList();

        // 通过账号检查是否有重复数据
        List<UserInfoEO> repeatUserList = listRepeatUserByAccount(sourceDataList);
        if (CollectionUtils.isNotEmpty(repeatUserList)) {
            updateOldData(filterAndGetRepeatData(sourceDataList, repeatUserList), repeatUserList, excelImportBean);
        }

        // 导入新数据
        importNewData(filterAndGetNewData(sourceDataList, repeatUserList), excelImportBean);
    }

    /**
     * 停止导入，返回重复数据
     */
    private void importAndStopIfHasRepeatData(ExcelImportBean<T> excelImportBean) {
        // 通过账号检查是否有重复数据
        List<T> sourceDataList = excelImportBean.getSourceDataList();
        List<UserInfoEO> repeatUserList = listRepeatUserByAccount(sourceDataList);

        if (CollectionUtils.isNotEmpty(repeatUserList)) {
            excelImportBean.setFailDataList(listRepeatDataByAccount(sourceDataList, repeatUserList));
            excelImportBean.setResultCode("-1");
            excelImportBean.setFailReason("有重复数据，停止导入");
        } else {
            // 导入新数据
            importNewData(filterAndGetNewData(sourceDataList, repeatUserList), excelImportBean);
        }
    }

    /**
     * 导入新数据，忽略重复数据
     */
    private void importAndIgnoreRepeatData(ExcelImportBean<T> excelImportBean) {
        List<T> sourceDataList = excelImportBean.getSourceDataList();
        List<UserInfoEO> repeatUserList = listRepeatUserByAccount(sourceDataList);

        // 需要忽略的重复数据
        if (CollectionUtils.isNotEmpty(repeatUserList)) {
            excelImportBean.setFailDataList(listRepeatDataByAccount(sourceDataList, repeatUserList));
            excelImportBean.setFailReason("忽略重复数据");
        }

        // 导入新数据
        importNewData(filterAndGetNewData(sourceDataList, repeatUserList), excelImportBean);
    }

    /**
     * 更新重复数据
     */
    private void updateOldData(List<T> repeatDataList, List<UserInfoEO> userInfoListInDB, ExcelImportBean<T> importResult) {
        if (CollectionUtils.isEmpty(repeatDataList)) {
            return;
        }

        final Map<String, UserInfoEO> userInfoMapInDB = ObjectUtils.asMapByFiled(userInfoListInDB, "account");
        for (T excelData : repeatDataList) {
            if (userInfoMapInDB != null && userInfoMapInDB.containsKey(excelData.getAccount())) {
                UserInfoEO userInfoInDB = userInfoMapInDB.get(excelData.getAccount());

                // 验证用户是否存在
                if (userInfoInDB == null) {
                    importResult.getFailDataList().add(new ExcelImportFailDataBean<T>("用户信息不存在", excelData));
                    continue;
                }
                if (userInfoInDB.getType() != getUserType()) {
                    // 这条数据不是当前用户类型的，比如专家数据和管理员数据会有同名情况，但是不能更新数据
                    importResult.getFailDataList().add(new ExcelImportFailDataBean<T>("已经存在同登录名账户", excelData));
                    continue;
                }

                this.update(excelData, userInfoInDB, importResult);
            }
        }
    }

    /**
     * 添加新数据
     */
    private void importNewData(List<T> newDataList, ExcelImportBean<T> importResult) {
        prepareData(newDataList, importResult);

        for (T manageUser : newDataList) {
            this.save(manageUser, importResult);
        }
    }

    /**
     * 检查并获取数据库中已有的重复数据
     *
     * @param excelDataList    Excel数据
     * @param userInfoListInDB 数据库中的数据
     */
    private List<ExcelImportFailDataBean<T>> listRepeatDataByAccount(List<T> excelDataList, List<UserInfoEO> userInfoListInDB) {
        List<ExcelImportFailDataBean<T>> repeatDataList = new LinkedList<>();
        List<T> listDataInDB = filterAndGetRepeatData(excelDataList, userInfoListInDB);
        if (listDataInDB != null) {
            for (T excelUser : listDataInDB) {
                ExcelImportFailDataBean<T> resultBean = new ExcelImportFailDataBean<>("登录账号重复", excelUser);
                repeatDataList.add(resultBean);
            }
        }
        return repeatDataList;
    }

    /**
     * 过滤得到数据库中已有的重复数据（过滤掉新数据，获得重复数据）
     *
     * @param excelDataList    Excel数据
     * @param userInfoListInDB 数据库中的数据
     */
    private List<T> filterAndGetRepeatData(List<T> excelDataList, List<UserInfoEO> userInfoListInDB) {
        final Map<String, UserInfoEO> userInfoMapInDB = ObjectUtils.asMapByFiled(userInfoListInDB, "account");
        if (userInfoListInDB == null || userInfoMapInDB == null) {
            return null;
        }
        return excelDataList.stream().filter(item -> userInfoMapInDB.containsKey(item.getAccount())).collect(Collectors.toList());
    }

    /**
     * 过滤得到数据库中没有的新数据（过滤掉重复数据，留下新数据）
     *
     * @param excelDataList    Excel数据
     * @param userInfoListInDB 数据库中的数据
     */
    private List<T> filterAndGetNewData(List<T> excelDataList, List<UserInfoEO> userInfoListInDB) {
        final Map<String, UserInfoEO> userInfoMapInDB = ObjectUtils.asMapByFiled(userInfoListInDB, "account");
        if (userInfoListInDB == null || userInfoMapInDB == null) {
            return excelDataList;
        }
        return excelDataList.stream().filter(item -> !userInfoMapInDB.containsKey(item.getAccount())).collect(Collectors.toList());
    }

    /**
     * 通过账户获取数据库中已有的重复数据
     *
     * @param excelDataList Excel数据
     * @return
     */
    private List<UserInfoEO> listRepeatUserByAccount(List<T> excelDataList) {
        return userInfoEOService.listByAccount(ObjectUtils.getFieldValueList(excelDataList, "account"), getUserType());
    }

}
