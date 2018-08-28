package com.lcw.one.user.bean.vo;


public class ExcelImportFailDataBean<T extends UserImportVO> implements Comparable {

    private Integer index;
    private String failReason;
    private T data;

    public ExcelImportFailDataBean(String failReason, T data) {
        this.index = data.getIndex();
        this.failReason = failReason;
        this.data = data;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public int compareTo(Object o) {
        ExcelImportFailDataBean<T> that = (ExcelImportFailDataBean<T>) o;
        return Integer.compare(getIndex(), that.getIndex());
    }
}
