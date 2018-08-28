package com.lcw.one.workflow.audit;

import org.activiti.engine.impl.persistence.entity.TaskEntity;

/**
 * 给工作流节点分配用户
 */
public interface IActivitiAssign {

    void assign(TaskEntity taskEntity);

}
