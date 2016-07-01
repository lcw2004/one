package com.lcw.one.modules.esign.entity;

import com.lcw.one.common.persistence.IdEntity;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by lcw on 16-6-17.
 */
@Entity
@Table(name = "E_SEAL")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Seal extends IdEntity<Seal> {
    private String sealName;
    private Integer sealType;
    private String sealToken;
    private Certificate cert;
    private String sealOwnerName;
    private String sealOwnerId;
    private byte[] sealImgContent;
    private Integer signCount;

    @Basic
    @Column(name = "SEAL_NAME")
    public String getSealName() {
        return sealName;
    }

    public void setSealName(String sealName) {
        this.sealName = sealName;
    }

    @Basic
    @Column(name = "SEAL_TYPE")
    public Integer getSealType() {
        return sealType;
    }

    public void setSealType(Integer sealType) {
        this.sealType = sealType;
    }

    @Basic
    @Column(name = "SEAL_TOKEN")
    public String getSealToken() {
        return sealToken;
    }

    public void setSealToken(String sealToken) {
        this.sealToken = sealToken;
    }

    @OneToOne
    @JoinColumn(name="CERT_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    @NotNull
    public Certificate getCert() {
        return cert;
    }

    public void setCert(Certificate cert) {
        this.cert = cert;
    }

    public byte[] getSealImgContent() {
        return sealImgContent;
    }

    public void setSealImgContent(byte[] sealImgContent) {
        this.sealImgContent = sealImgContent;
    }

    public String getSealOwnerName() {
        return sealOwnerName;
    }

    public void setSealOwnerName(String sealOwnerName) {
        this.sealOwnerName = sealOwnerName;
    }

    public String getSealOwnerId() {
        return sealOwnerId;
    }

    public void setSealOwnerId(String sealOwnerId) {
        this.sealOwnerId = sealOwnerId;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }
}
