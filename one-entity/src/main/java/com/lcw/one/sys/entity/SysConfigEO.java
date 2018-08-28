package com.lcw.one.sys.entity;

import javax.persistence.*;

/**
 * @version 2017-08-18.
 * @auth Licw
 */
@Entity
@Table(name = "sys_config")
public class SysConfigEO {
    private String configId;
    private String typeCode;
    private String configName;
    private String configKey;
    private String configValue;
    private String remark;

    @Id
    @Column(name = "config_id")
    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    @Basic
    @Column(name = "type_code")
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Basic
    @Column(name = "config_name")
    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    @Basic
    @Column(name = "config_key")
    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    @Basic
    @Column(name = "config_value")
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysConfigEO that = (SysConfigEO) o;

        if (configId != null ? !configId.equals(that.configId) : that.configId != null) return false;
        if (typeCode != null ? !typeCode.equals(that.typeCode) : that.typeCode != null) return false;
        if (configKey != null ? !configKey.equals(that.configKey) : that.configKey != null) return false;
        if (configValue != null ? !configValue.equals(that.configValue) : that.configValue != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configId != null ? configId.hashCode() : 0;
        result = 31 * result + (typeCode != null ? typeCode.hashCode() : 0);
        result = 31 * result + (configKey != null ? configKey.hashCode() : 0);
        result = 31 * result + (configValue != null ? configValue.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
