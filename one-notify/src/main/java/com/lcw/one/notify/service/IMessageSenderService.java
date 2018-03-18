package com.lcw.one.notify.service;

import java.util.List;
import java.util.Map;

public interface IMessageSenderService {


    /**
     * 发送Email到用户邮箱
     *
     * @param email
     * @param title
     * @param content
     */
    void sendEmail(String email, String title, String content);

    /**
     * 发送短信到指定手机号码
     *
     * @param mobile
     * @param content
     */
    void sendSMS(String mobile, String content);

    /**
     * 发送WebSocket消息（未定义）
     */
    void sendWebSocket();

    /**
     * 发送系统内定义的模板消息
     *
     * @param userId     用户ID
     * @param templateId     模板ID
     * @param placeholderMap 占位符列表
     */
    void sendMessage(String userId, String templateId, Map<String, Object> placeholderMap);

    /**
     * 发送系统内定义的模板消息
     *
     * @param userIdList     用户ID列表
     * @param templateId     模板ID
     * @param placeholderMap 占位符列表
     */
    void sendMessage(List<String> userIdList, String templateId, Map<String, Object> placeholderMap);

    /**
     * 发送系统内定义的模板消息
     *
     * @param roleIdList     用户角色ID列表
     * @param templateId     模板ID
     * @param placeholderMap 占位符列表
     */
    void sendMessageByRoleId(List<String> roleIdList, String templateId, Map<String, Object> placeholderMap);

    /**
     * 发送系统内定义的模板消息
     *
     * @param officeIdList   用户机构ID列表
     * @param templateId     模板ID
     * @param placeholderMap 占位符列表
     */
    void sendMessageByOfficeId(List<String> officeIdList, String templateId, Map<String, Object> placeholderMap);

}
