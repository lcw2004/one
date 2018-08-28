package com.lcw.one.notify.channel.sms.impl;

import com.lcw.one.notify.channel.base.EmailService;
import com.lcw.one.notify.channel.sms.ISmSSender;
import com.lcw.one.util.exception.OneBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmptySmsSender implements ISmSSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Override
    public void sendSms(String mobile, String content) {
        logger.info("Send SMS To:[{}], title:[{}], Content:[{}]", mobile, content);
        try {
            // 短信发送实现
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("短信发送失败，异常：" + e.getMessage());
        }
    }

    @Override
    public void sendSms(List<String> mobileList, String content) {
        logger.info("Send SMS To:[{}], title:[{}], Content:[{}]", mobileList, content);
        try {
            // 短信发送实现
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("短信发送失败，异常：" + e.getMessage());
        }
    }
}
