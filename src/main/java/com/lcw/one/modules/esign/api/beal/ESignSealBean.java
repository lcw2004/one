package com.lcw.one.modules.esign.api.beal;

import com.lcw.one.modules.esign.entity.Certificate;
import com.lcw.one.modules.esign.entity.Seal;

public class ESignSealBean {
    private String sealId;
    private String sealName;
    private Integer sealType;
    private String sealToken;
    private byte[] sealImgContent;
    private String certId;
    private byte[] certContent;
    private String certPassword;
    private String sealOwnerName;
    private String sealOwnerId;

    public ESignSealBean(Seal seal) {
        this.sealId = seal.getId();
        this.sealName = seal.getSealName();
        this.sealType = seal.getSealType();
        this.sealToken = seal.getSealToken();
        this.sealImgContent = seal.getSealImgContent();
        this.sealOwnerId = seal.getSealOwnerId();
        this.sealOwnerName = seal.getSealOwnerName();
        if(seal.getCert() != null) {
            Certificate certificate = seal.getCert();
            this.certId = certificate.getId();
            this.certContent = certificate.getContent();
            this.certPassword = certificate.getPassword();
        }
    }

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

    public Integer getSealType() {
        return sealType;
    }

    public void setSealType(Integer sealType) {
        this.sealType = sealType;
    }

    public String getSealToken() {
        return sealToken;
    }

    public void setSealToken(String sealToken) {
        this.sealToken = sealToken;
    }

    public byte[] getSealImgContent() {
        return sealImgContent;
    }

    public void setSealImgContent(byte[] sealImgContent) {
        this.sealImgContent = sealImgContent;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public byte[] getCertContent() {
        return certContent;
    }

    public void setCertContent(byte[] certContent) {
        this.certContent = certContent;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
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
}
