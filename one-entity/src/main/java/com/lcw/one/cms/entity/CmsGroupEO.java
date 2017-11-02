package com.lcw.one.cms.entity;

import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.*;

@Entity
@Table(name = "cms_group")
public class CmsGroupEO extends TreeEntity<CmsGroupEO> {

    private Integer orderIndex;
    private String keyword;
    private String remark;
    private String url;

    @Basic
    @Column(name = "order_index")
    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Basic
    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
