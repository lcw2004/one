package com.lcw.one.modules.esign.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcw.one.common.persistence.IdEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "E_CERTIFICATE")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Certificate extends IdEntity<Certificate> {
    private String password;
    private byte[] content;
    private String serialNumber;
    private String issuerDN;
    private String subjectDN;
    private String certRoot;
    private Date notBefore;
    private Date notAfter;
    private String certOwnerName;
    private String certOwnerId;
    private String certType;

    @Length(min=0, max=20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Column(name = "ISSUER_DN")
    public String getIssuerDN() {
        return issuerDN;
    }

    public void setIssuerDN(String issuerDN) {
        this.issuerDN = issuerDN;
    }

    @Column(name = "SUBJECT_DN")
    public String getSubjectDN() {
        return subjectDN;
    }

    public void setSubjectDN(String subjectDN) {
        this.subjectDN = subjectDN;
    }



    @Length(min=0, max=100)
    public String getCertRoot() {
        return certRoot;
    }

    public void setCertRoot(String certRoot) {
        this.certRoot = certRoot;
    }

    @Length(min=0, max=100)
    public String getCertOwnerName() {
        return certOwnerName;
    }

    public void setCertOwnerName(String certOwnerName) {
        this.certOwnerName = certOwnerName;
    }


    @Length(min=0, max=100)
    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
    }
}
