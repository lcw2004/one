package com.lcw.one.notify.service;

import com.lcw.one.notify.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {

    private static Logger logger = LoggerFactory.getLogger(NotifyService.class);

    @Autowired
    private EmailSender emailSender;

    public void sendEmail(String email, String content) {
        logger.info("=========== Send Email Start ===========");

        emailSender.sendEmail(email, "注册验证码", content);

        logger.info("=========== Send Email End ===========");
    }

    public void sendSMS(String phone, String content) {
        logger.info("=========== Send SMS Start ===========");
        logger.info("To:[{}], Content:[{}]", phone, content);
        logger.info("=========== Send SMS End ===========");
    }

    public void sendNotify(String userId, String content) {
        logger.info("=========== Send Notify Start ===========");
        logger.info("To:[{}], Content:[{}]", userId, content);
        logger.info("=========== Send Notify End ===========");
    }
}
