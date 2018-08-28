package com.lcw.one.workflow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;

/**
 * 审核变更详情
 */
@Entity
@Table(name = "flow_audit_item_detail")
public class FlowAuditItemDetailEO {

	/**
	 * 审核变更详情ID
	 */
	@Id
	@Column(name = "detail_id")
	private String detailId;

	/**
	 * 审核事项ID
	 */
	@Basic
	@Column(name = "audit_item_id")
	private String auditItemId;

	/**
	 * 属性名称
	 */
	@Basic
	@Column(name = "attribute_name")
	private String attributeName;

    /**
     * 属性类型（1 - 字符串，2 - 日期，3 - 时间，4 - 字典，5 - 图片）
     */
	@Basic
    @Column(name = "attribute_type")
    private Integer attributeType;

	/**
	 * 变更前的值
	 */
	@Basic
	@Column(name = "origin_value")
	private String originValue;

	/**
	 * 变更前的显示值
	 */
	@Basic
	@Column(name = "origin_label")
	private String originLabel;

	/**
	 * 变更后的值
	 */
	@Basic
	@Column(name = "target_value")
	private String targetValue;

	/**
	 * 变更后的显示值
	 */
	@Basic
	@Column(name = "target_label")
	private String targetLabel;


    public String getDetailId () {
        return this.detailId;
    }

    public void setDetailId (String detailId) {
        this.detailId = detailId;
    }

    public String getAuditItemId () {
        return this.auditItemId;
    }

    public void setAuditItemId (String auditItemId) {
        this.auditItemId = auditItemId;
    }

    public String getAttributeName () {
        return this.attributeName;
    }

    public void setAttributeName (String attributeName) {
        this.attributeName = attributeName;
    }

    public Integer getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(Integer attributeType) {
        this.attributeType = attributeType;
    }

    public String getOriginValue () {
        return this.originValue;
    }

    public void setOriginValue (String originValue) {
        this.originValue = originValue;
    }

    public String getOriginLabel () {
        return this.originLabel;
    }

    public void setOriginLabel (String originLabel) {
        this.originLabel = originLabel;
    }

    public String getTargetValue () {
        return this.targetValue;
    }

    public void setTargetValue (String targetValue) {
        this.targetValue = targetValue;
    }

    public String getTargetLabel () {
        return this.targetLabel;
    }

    public void setTargetLabel (String targetLabel) {
        this.targetLabel = targetLabel;
    }

}