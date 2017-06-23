package com.lcw.one.workflow.audit;

import com.lcw.one.workflow.constant.WorkflowDefEnum;

import java.util.HashMap;
import java.util.Map;

public class WorkflowDefHandlerMapper {

    private static Map<WorkflowDefEnum, Class> handlerMapper = new HashMap<>();

    static {
    }

    public static Class getHandlerClass(WorkflowDefEnum workflowDefEnum) {
        return handlerMapper.get(workflowDefEnum);
    }
}
