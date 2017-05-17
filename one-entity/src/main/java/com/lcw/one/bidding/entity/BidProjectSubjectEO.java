package com.lcw.one.bidding.entity;

import com.lcw.one.util.annotation.ExcelField;

import javax.persistence.*;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_project_subject")
public class BidProjectSubjectEO {

    private String projectId;

    @ExcelField(title = "物料名称", column = 0)
    private String name;

    @ExcelField(title = "数量", column = 1)
    private Integer count;

    @ExcelField(title = "计量单位", column = 2)
    private String unit;

    @ExcelField(title = "规格型号", column = 3)
    private String model;

    @ExcelField(title = "交货地点", column = 4)
    private String place;

    @ExcelField(title = "备注", column = 5)
    private String remark;

    @ExcelField(title = "参考价", column = 6)
    private Double referencePrice;

    private Double floorPrice;

    private String subjectId;

    @Basic
    @Column(name = "project_id")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "reference_price")
    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
    }

    @Basic
    @Column(name = "floor_price")
    public Double getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Double floorPrice) {
        this.floorPrice = floorPrice;
    }

    @Id
    @Column(name = "subject_id")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

}
