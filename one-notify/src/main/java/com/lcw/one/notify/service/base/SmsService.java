package com.lcw.one.notify.service.base;

import com.lcw.one.notify.sms.impl.EmptySmsSender;
import com.lcw.one.util.exception.OneBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SmsService {

    @Autowired
    private EmptySmsSender emptySmsSender;

    public void sendSms(String mobile, String content) {
        emptySmsSender.sendSms(mobile, content);
    }

    public void sendSms(List<String> mobileList, String content) {
        emptySmsSender.sendSms(mobileList, content);
    }

}
