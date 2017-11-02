package com.lcw.one.sys.entity;

import javax.persistence.*;

/**
 * @version 2017-08-22.
 * @auth Licw
 */
@Entity
@Table(name = "base_template")
public class BaseTemplateEO {
    private String templateId;
    private String content;
    private String name;
    private Integer isDefault;
    private String code;
    private BaseTemplateTypeEO templateType;

    @Id
    @Column(name = "template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "is_default")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @OneToOne
    @JoinColumn(name = "code", insertable = false, updatable = false)
    public BaseTemplateTypeEO getTemplateType() {
        return templateType;
    }

    public void setTemplateType(BaseTemplateTypeEO templateType) {
        this.templateType = templateType;
    }
}
