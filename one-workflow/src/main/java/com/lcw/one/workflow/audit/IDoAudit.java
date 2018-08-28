package com.lcw.one.workflow.audit;

import org.activiti.engine.delegate.DelegateExecution;

public interface IDoAudit {

    void doAgree(DelegateExecution execution);

    void doReject(DelegateExecution execution);

}
