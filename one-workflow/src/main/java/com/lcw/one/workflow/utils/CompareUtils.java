package com.lcw.one.workflow.utils;

import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.Reflections;
import com.lcw.one.workflow.entity.FlowAuditItemDetailEO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CompareUtils {

    private Object before;
    private Object after;
    private List<FlowAuditItemDetailEO> detailList = new LinkedList<>();

    public CompareUtils(Object before, Object after) {
        this.before = before;
        this.after = after;
    }

    public void compare(String fieldName, String attributeName) {
        Object beforeValue = Reflections.getFieldValue(this.before, fieldName);
        Object afterValue = Reflections.getFieldValue(this.after, fieldName);
        compare(attributeName, 1, beforeValue, afterValue);
    }

    public void compare(String fieldName, String attributeName, Integer attributeType) {
        Object beforeValue = Reflections.getFieldValue(this.before, fieldName);
        Object afterValue = Reflections.getFieldValue(this.after, fieldName);
        compare(attributeName, attributeType, beforeValue, afterValue);
    }

    public List<FlowAuditItemDetailEO> getCompareResult() {
        return this.detailList;
    }

    private void compare(String attributeName, Integer attributeType, Object beforeUpdate, Object afterUpdate) {
        String beforeUpdateStr = valueOf(beforeUpdate, attributeType);
        String afterUpdateStr = valueOf(afterUpdate, attributeType);
        this.compare(attributeName, attributeType, beforeUpdateStr, afterUpdateStr, beforeUpdateStr, afterUpdateStr);
    }

    public void compare(String attributeName, Integer attributeType, String originValueStr, String targetValueStr, String originLabel, String targetLabel) {
        if (!originValueStr.equals(targetValueStr)) {
            FlowAuditItemDetailEO detailEO = new FlowAuditItemDetailEO();
            detailEO.setAttributeName(attributeName);
            detailEO.setAttributeType(attributeType);
            detailEO.setOriginLabel(originLabel);
            detailEO.setOriginValue(originValueStr);
            detailEO.setTargetLabel(targetLabel);
            detailEO.setTargetValue(targetValueStr);
            this.detailList.add(detailEO);
        }
    }

    private static String valueOf(Object val, Integer attributeType) {
        if (attributeType == 2 && val instanceof Date) {
            return DateUtils.dateToString((Date) val, DateUtils.yyyy_MM_dd_EN);
        } else if (attributeType == 3 && val instanceof Date) {
            return DateUtils.dateToString((Date) val, DateUtils.yyyy_MM_dd_HH_mm_ss_EN);
        } else {
            return String.valueOf(val);
        }
    }

}
