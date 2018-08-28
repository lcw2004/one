package com.lcw.one.workflow.service;

import com.lcw.one.base.utils.OneLinkUtils;
import com.lcw.one.notify.service.IMessageSenderService;
import com.lcw.one.sys.service.SysUserRoleEOService;
import com.lcw.one.user.service.UserManagerEOService;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.workflow.bean.CandidateUtils;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkFlowAssignService {

    @Autowired
    private IMessageSenderService iMessageSenderService;

    @Autowired
    private UserManagerEOService userManagerEOService;

    @Autowired
    private SysUserRoleEOService sysUserRoleEOService;

    public void assignToUserList(TaskEntity taskEntity, List<String> userIdList) {
        taskEntity.setVariableLocal("auditTarget", "用户:" + StringUtils.listToString(userIdList));
        for (String userId: userIdList) {
            taskEntity.addCandidateGroup(CandidateUtils.user(userId));
        }
        this.notify(taskEntity, userIdList);
    }

    public void assignToUser(TaskEntity taskEntity, String userId) {
        taskEntity.setAssignee(userId);
        taskEntity.setVariableLocal("auditTarget", "用户:" + userId);
        this.notifyByUser(taskEntity, userId);
    }

    public void assignToRole(TaskEntity taskEntity, String roleId) {
        taskEntity.addCandidateGroup(CandidateUtils.role(roleId));
        taskEntity.setVariableLocal("auditTarget", "角色:" + roleId);
        this.notifyByRole(taskEntity, roleId);
    }

    public void assignToOffice(TaskEntity taskEntity, String officeId) {
        taskEntity.addCandidateGroup(CandidateUtils.office(officeId));
        taskEntity.setVariableLocal("auditTarget", "机构:" + officeId);
        this.notifyByOffice(taskEntity, officeId);
    }

    public void assignToOfficeAndRole(TaskEntity taskEntity, String officeId, String roleId) {
        taskEntity.addCandidateGroup(CandidateUtils.officeAndRole(officeId, roleId));
        taskEntity.setVariableLocal("auditTarget", "机构:" + officeId + " + 角色:" + roleId);
        this.notifyByOfficeAndRole(taskEntity, officeId, roleId);
    }

    private void notifyByUser(TaskEntity taskEntity, String userId) {
        this.notify(taskEntity, Arrays.asList(userId));
    }

    private void notifyByRole(TaskEntity taskEntity, String roleId) {
        notify(taskEntity, sysUserRoleEOService.listUserIdByRoleId(roleId));
    }

    private void notifyByOffice(TaskEntity taskEntity, String officeId) {
        notify(taskEntity, userManagerEOService.listUserIdByOfficeId(officeId));
    }

    private void notifyByOfficeAndRole(TaskEntity taskEntity, String officeId, String roleId) {
        notify(taskEntity, userManagerEOService.listUserIdByOfficeIdAndRoleId(officeId, roleId));
    }

    private void notify(TaskEntity taskEntity, List<String> userIdList) {
        String processDefinitionId = taskEntity.getProcessDefinitionId();
        processDefinitionId = processDefinitionId.substring(0, processDefinitionId.indexOf(":"));
        String businessId = (String) taskEntity.getVariable("businessId");
        String businessName = (String) taskEntity.getVariable("businessName");
        String applyUserName = (String) taskEntity.getVariable("applyUserName");
        String auditType = (String) taskEntity.getVariable("processName_");
        String businessLink = OneLinkUtils.getBusinessLink(processDefinitionId, businessId, businessName);

        Map<String, Object> placeholderMap = new HashMap<>();
        placeholderMap.put("applyUserName", applyUserName);
        placeholderMap.put("auditType", auditType);
        placeholderMap.put("businessName", businessLink);

        iMessageSenderService.sendMessage(userIdList, "10010", placeholderMap);
    }
}
