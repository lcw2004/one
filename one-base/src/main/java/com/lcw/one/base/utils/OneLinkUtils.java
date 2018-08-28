package com.lcw.one.base.utils;

import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.workflow.constant.WorkflowDefEnum;

public class OneLinkUtils {

    public static String getBusinessLink(String businessType, String businessId, String businessName) {
        WorkflowDefEnum workflowDefEnum = WorkflowDefEnum.getByFlowId(businessType);
        if (workflowDefEnum == WorkflowDefEnum.PlanPublishAudit) {
            return getPlanLink(businessId, businessName);
        } else if (workflowDefEnum == WorkflowDefEnum.ProjectPublishAudit) {
            return getProjectLink(businessId, businessName);
        } else if (workflowDefEnum == WorkflowDefEnum.ProjectAwardAudit) {
            return getProjectAwardLink(businessId, businessName);
        } else if (workflowDefEnum == WorkflowDefEnum.ProjectSupplierApplyAudit) {
            return getSupplierLink(businessId, businessName);
        } else if (workflowDefEnum == WorkflowDefEnum.ContractAudit) {
            return getContractLink(businessId, businessName);
        } else if (workflowDefEnum == WorkflowDefEnum.SupplierRegistryApply) {
            return getSupplierLink(businessId, businessName);
        } else if (workflowDefEnum == WorkflowDefEnum.SupplierInfoChange) {
            return getSupplierLink(businessId, businessName);
        }
        return "";
    }

    private static String buildATag(String href, String content) {
        return "<a href=\"" + href + "\">" + content + "</a>";
    }

    private static String getPlanLink(String businessId, String businessName) {
        return buildATag("#/plan/" + businessId + "/view", businessName);
    }

    private static String getProjectLink(String businessId, String businessName) {
        return buildATag("#/bid/project/" + businessId + "/view", businessName);
    }

    private static String getProjectAwardLink(String businessId, String businessName) {
        return buildATag("#/bid/project/" + businessId + "/award-view", businessName);
    }

    private static String getContractLink(String businessId, String businessName) {
        return buildATag("#/contract/" + businessId + "/view", businessName);
    }

    private static String getSupplierLink(String businessId, String businessName) {
        return buildATag("#/user/supplier/" + businessId + "/view", businessName);
    }

    public static String getAuditLink(String taskId, String content) {
        return buildATag("#/task/todo/" + taskId, content);
    }

    public static String getAuditViewLink(String taskId, String content) {
        return buildATag("#/task/todo/" + taskId + "/view", content);
    }

    public static String getFileDownloadUrl(String fileId) {
        return StringUtils.isEmpty(fileId) ? "" : "/api/sys/file/" + fileId + "/download";
    }

}
