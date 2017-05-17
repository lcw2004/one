package com.lcw.one.workflow.audit;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.SpringContextHolder;
import com.lcw.one.workflow.constant.WorkflowDefEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDoAuditFactory {

    private static final Logger logger = LoggerFactory.getLogger(IDoAuditFactory.class);

    public static IDoAudit buildIDoAudit(String flowId) {
        if (StringUtils.isEmpty(flowId)) {
            throw new OneBaseException("FlowId不能为空");
        }

        WorkflowDefEnum workflowDefEnum = WorkflowDefEnum.getByFlowId(flowId);
        if (workflowDefEnum == null) {
            throw new OneBaseException("找不到流程[" + flowId + "]对应的处理器枚举");
        }

        Class handlerClass = WorkflowDefHandlerMapper.getHandlerClass(workflowDefEnum);
        if(handlerClass == null) {
            throw new OneBaseException("找不到流程[" + flowId + "]对应的处理器类");
        }

        IDoAudit iDoAudit = (IDoAudit) SpringContextHolder.getBean(handlerClass);
        if (iDoAudit == null) {
            throw new OneBaseException("找不到流程[" + flowId + "]对应的处理器类");
        }
        logger.info("流程[" + flowId + "], 处理器[" + iDoAudit.getClass() + "]");

        return iDoAudit;
    }

}
