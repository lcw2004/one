package com.lcw.one.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcw.one.user.constant.UserSupplierStatusEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_supplier")
public class UserSupplierEO {
    private String supplierId;
    private String name;
    private String shortName;
    private String usedName;
    private Date createTime;
    private Integer status;
    private String statusCn;
    private String legalPersonName;
    private String legalPersonNumber;
    private String officeCode;
    private String businessType;
    private Integer officeNature;
    private Integer officeType;
    private String officeAddress;
    private String areaId;

    // 原附加信息
    private String emploeeCount;
    private String enterpriseScale;
    private String mainBusiness;
    private String businessProfile;
    private String zipCode;
    private String telephone;
    private String fax;
    private String email;
    private String bankName;
    private String bankAccount;
    private String supplyArea;
    private String registerType;
    private String registerCapital;
    private String remark;

    private UserInfoEO principalUser;
    private UserSupplierUserEO principalUserInfo;
    private List<UserSupplierPurchaseTypeRelationEO> purchaseTypeList;
    private List<UserSupplierQualificationEO> qualificationList;

    @Id
    @Column(name = "supplier_id")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "used_name")
    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "legal_person_name")
    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    @Basic
    @Column(name = "legal_person_number")
    public String getLegalPersonNumber() {
        return legalPersonNumber;
    }

    public void setLegalPersonNumber(String legalPersonNumber) {
        this.legalPersonNumber = legalPersonNumber;
    }

    @Basic
    @Column(name = "office_code")
    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    @Basic
    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "office_nature")
    public Integer getOfficeNature() {
        return officeNature;
    }

    public void setOfficeNature(Integer officeNature) {
        this.officeNature = officeNature;
    }

    @Basic
    @Column(name = "office_type")
    public Integer getOfficeType() {
        return officeType;
    }

    public void setOfficeType(Integer officeType) {
        this.officeType = officeType;
    }

    @Basic
    @Column(name = "office_address")
    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    @Basic
    @Column(name = "area_id")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name="principal_user_id")
    public UserInfoEO getPrincipalUser() {
        return principalUser;
    }

    public void setPrincipalUser(UserInfoEO principalUser) {
        this.principalUser = principalUser;
    }

    @Transient
    public String getStatusCn() {
        statusCn = UserSupplierStatusEnum.get(status).getLabel();
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    @Basic
    @Column(name = "emploee_count")
    public String getEmploeeCount() {
        return emploeeCount;
    }

    public void setEmploeeCount(String emploeeCount) {
        this.emploeeCount = emploeeCount;
    }

    @Basic
    @Column(name = "enterprise_scale")
    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale;
    }

    @Basic
    @Column(name = "main_business")
    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    @Basic
    @Column(name = "business_profile")
    public String getBusinessProfile() {
        return businessProfile;
    }

    public void setBusinessProfile(String businessProfile) {
        this.businessProfile = businessProfile;
    }

    @Basic
    @Column(name = "zip_code")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    @Column(name = "bank_account")
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Basic
    @Column(name = "supply_area")
    public String getSupplyArea() {
        return supplyArea;
    }

    public void setSupplyArea(String supplyArea) {
        this.supplyArea = supplyArea;
    }

    @Basic
    @Column(name = "register_type")
    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    @Basic
    @Column(name = "register_capital")
    public String getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public UserSupplierUserEO getPrincipalUserInfo() {
        return principalUserInfo;
    }

    public void setPrincipalUserInfo(UserSupplierUserEO principalUserInfo) {
        this.principalUserInfo = principalUserInfo;
    }

    @Transient
    public List<UserSupplierPurchaseTypeRelationEO> getPurchaseTypeList() {
        return purchaseTypeList;
    }

    public void setPurchaseTypeList(List<UserSupplierPurchaseTypeRelationEO> purchaseTypeList) {
        this.purchaseTypeList = purchaseTypeList;
    }

    @Transient
    public List<UserSupplierQualificationEO> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<UserSupplierQualificationEO> qualificationList) {
        this.qualificationList = qualificationList;
    }
}
