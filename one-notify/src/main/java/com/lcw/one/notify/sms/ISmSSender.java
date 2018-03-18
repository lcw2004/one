package com.lcw.one.notify.sms;

import java.util.List;

/**
 * 短信接口定义
 */
public interface ISmSSender {

    /**
     * 单条发送消息
     * @param mobile
     * @param content
     */
    void sendSms(String mobile, String content);

    /**
     * 批量发送短信
     * @param mobileList
     * @param content
     */
    void sendSms(List<String> mobileList, String content);

}
