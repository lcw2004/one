package com.lcw.one.user.bean;

import com.lcw.one.util.http.bean.BaseQueryCondition;

public class SupplierQueryCondition extends BaseQueryCondition {

    private String supplierLikeName;
    private String legalPersonLikeName;
    private String principalUserLikeName;
    private String officeCode;
    private String supplierStatus;
    private String registryTimeFrom;
    private String registryTimeTo;
    private String officeAddress;
    private String supplyArea;

    public String getSupplierLikeName() {
        return supplierLikeName;
    }

    public void setSupplierLikeName(String supplierLikeName) {
        this.supplierLikeName = supplierLikeName;
    }

    public String getLegalPersonLikeName() {
        return legalPersonLikeName;
    }

    public void setLegalPersonLikeName(String legalPersonLikeName) {
        this.legalPersonLikeName = legalPersonLikeName;
    }

    public String getPrincipalUserLikeName() {
        return principalUserLikeName;
    }

    public void setPrincipalUserLikeName(String principalUserLikeName) {
        this.principalUserLikeName = principalUserLikeName;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getRegistryTimeFrom() {
        return registryTimeFrom;
    }

    public void setRegistryTimeFrom(String registryTimeFrom) {
        this.registryTimeFrom = registryTimeFrom;
    }

    public String getRegistryTimeTo() {
        return registryTimeTo;
    }

    public void setRegistryTimeTo(String registryTimeTo) {
        this.registryTimeTo = registryTimeTo;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getSupplyArea() {
        return supplyArea;
    }

    public void setSupplyArea(String supplyArea) {
        this.supplyArea = supplyArea;
    }
}
