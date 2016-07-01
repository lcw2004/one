package com.lcw.one.modules.esign.entity;

import com.lcw.one.common.persistence.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lcw on 16-6-17.
 */
@Entity
@Table(name = "E_SEAL_SIGN_LOG")
public class SealSignLog  extends IdEntity<Seal> {
    private String sealId;
    private String sealName;
    private String signUserId;
    private String signUserName;
    private String signMethod;
    private boolean signResult;
    private Date signTime;
    private String mac;
    private String ip;
    private String fileName;

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    public String getSealName() {
        return sealName;
    }

    public void setSealName(String sealName) {
        this.sealName = sealName;
    }

    public String getSignUserId() {
        return signUserId;
    }

    public void setSignUserId(String signUserId) {
        this.signUserId = signUserId;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean getSignResult() {
        return signResult;
    }

    public void setSignResult(boolean signResult) {
        this.signResult = signResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SealSignLog that = (SealSignLog) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sealId != null ? !sealId.equals(that.sealId) : that.sealId != null) return false;
        if (sealName != null ? !sealName.equals(that.sealName) : that.sealName != null) return false;
        if (signUserId != null ? !signUserId.equals(that.signUserId) : that.signUserId != null) return false;
        if (signUserName != null ? !signUserName.equals(that.signUserName) : that.signUserName != null) return false;
        if (signMethod != null ? !signMethod.equals(that.signMethod) : that.signMethod != null) return false;
        if (signTime != null ? !signTime.equals(that.signTime) : that.signTime != null) return false;
        if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sealId != null ? sealId.hashCode() : 0);
        result = 31 * result + (sealName != null ? sealName.hashCode() : 0);
        result = 31 * result + (signUserId != null ? signUserId.hashCode() : 0);
        result = 31 * result + (signUserName != null ? signUserName.hashCode() : 0);
        result = 31 * result + (signMethod != null ? signMethod.hashCode() : 0);
        result = 31 * result + (signTime != null ? signTime.hashCode() : 0);
        result = 31 * result + (mac != null ? mac.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
