package com.lcw.one.workflow;

import com.lcw.one.util.utils.Reflections;
import com.lcw.one.workflow.entity.FlowAuditItemDetailEO;

import java.util.Map;

public class AuditUtils {

    public static <T> void compare(Map<String, FlowAuditItemDetailEO> otherInfoMap, T before, T after, String attributeName, String fieldName) {
        compare(otherInfoMap, attributeName, Reflections.getFieldValue(before, fieldName), Reflections.getFieldValue(after, fieldName));
    }

    public static void compare(Map<String, FlowAuditItemDetailEO> otherInfoMap, String attributeName, Object beforeUpdate, Object afterUpdate) {
        String beforeUpdateStr = String.valueOf(beforeUpdate);
        String afterUpdateStr = String.valueOf(afterUpdate);
        if (beforeUpdateStr.equals(afterUpdateStr)) {
            return;
        }
        FlowAuditItemDetailEO detailEO = new FlowAuditItemDetailEO();
        detailEO.setAttributeName(attributeName);
        detailEO.setOriginLabel(beforeUpdateStr);
        detailEO.setOriginValue(beforeUpdateStr);
        detailEO.setTargetLabel(afterUpdateStr);
        detailEO.setTargetValue(afterUpdateStr);
        otherInfoMap.put(attributeName, detailEO);
    }

}
