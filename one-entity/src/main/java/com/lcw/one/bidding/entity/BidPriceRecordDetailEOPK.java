package com.lcw.one.bidding.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
public class BidPriceRecordDetailEOPK implements Serializable {
    private String recordId;
    private String subjectId;

    @Column(name = "record_id")
    @Id
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    @Column(name = "subject_id")
    @Id
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidPriceRecordDetailEOPK that = (BidPriceRecordDetailEOPK) o;

        if (recordId != null ? !recordId.equals(that.recordId) : that.recordId != null) return false;
        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordId != null ? recordId.hashCode() : 0;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }
}
