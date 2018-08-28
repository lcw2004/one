package com.lcw.one.workflow;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkFlowApplication.class)
public class HireProcessTest {

    private static final Logger logger = LoggerFactory.getLogger(HireProcessTest.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void TestStartProcess() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantName", "John Doe");
        variables.put("email", "john.doe@activiti.com");
        variables.put("phoneNumber", "123456789");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("taskListenerDelegateExpression", variables);
        logger.info("启动流程成功，流程ID：" + processInstance.getProcessInstanceId());

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        logger.info("完成任务：任务ID：" + task.getId());
        taskService.complete(task.getId());

        // Completing second task will set variable on process instance
        task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        logger.info("完成任务：任务ID：" + task.getId());
        taskService.complete(task.getId());
    }

}
