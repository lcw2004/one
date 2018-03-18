package com.lcw.one.notify.service.base;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemMessageService {

    public void sendMessage(List<String> userIdList, String templateId, Map<String, String> placeholderMap) {

    }

    public void sendMessageByRoleId(List<String> roleIdList, String templateId, Map<String, String> placeholderMap) {

    }

    public void sendMessageByOfficeId(List<String> officeIdList, String templateId, Map<String, String> placeholderMap) {

    }

}
