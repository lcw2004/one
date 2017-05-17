package com.lcw.one.bidding.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_price_record_detail")
@IdClass(BidPriceRecordDetailEOPK.class)
public class BidPriceRecordDetailEO {
    private String recordId;
    private Integer taxRatio;
    private Double price;
    private Double priceNoTax;
    private String subjectId;

    @Id
    @Column(name = "record_id")
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "tax_ratio")
    public Integer getTaxRatio() {
        return taxRatio;
    }

    public void setTaxRatio(Integer taxRatio) {
        this.taxRatio = taxRatio;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "price_no_tax")
    public Double getPriceNoTax() {
        return priceNoTax;
    }

    public void setPriceNoTax(Double priceNoTax) {
        this.priceNoTax = priceNoTax;
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
