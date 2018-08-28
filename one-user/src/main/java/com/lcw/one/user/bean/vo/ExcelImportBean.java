package com.lcw.one.user.bean.vo;

import com.lcw.one.util.http.ResponseMessageCodeEnum;

import java.util.*;

public class ExcelImportBean<T extends UserImportVO> {

    /**
     * 原始数据
     */
    private List<T> sourceDataList = new ArrayList<>();

    /**
     * 其他参数Map，用于承载一些每次导入需要传入的不同配置
     */
    private Map<String, Object> paramMap = new HashMap<>();

    /**
     * 数据总数量
     */
    private int totalCount;

    /**
     * 成功导入数据数量
     */
    private int successCount;

    /**
     * 本次导入结果的编码（成功为0）
     */
    private String resultCode = "0";

    /**
     * 本次导入失败的原因
     */
    private String failReason;

    /**
     * 失败的数据列表
     */
    private List<ExcelImportFailDataBean<T>> failDataList = new ArrayList<>();

    public void increaseSuccessCount() {
        successCount++;
    }

    public boolean isOk() {
        return this.resultCode.equals(ResponseMessageCodeEnum.SUCCESS.getCode());
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public List<T> getSourceDataList() {
        return sourceDataList;
    }

    public void setSourceDataList(List<T> sourceDataList) {
        this.sourceDataList = sourceDataList;
        this.totalCount = sourceDataList.size();
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public List<ExcelImportFailDataBean<T>> getFailDataList() {
        return failDataList;
    }

    public void setFailDataList(List<ExcelImportFailDataBean<T>> failDataList) {
        this.failDataList = failDataList;
    }


    public void sortFailData() {
        Collections.sort(getFailDataList());
    }
}
