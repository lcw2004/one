package com.lcw.one.notify.sender.impl;

import com.lcw.one.msg.entity.MsgMessageEO;
import com.lcw.one.notify.channel.system.dao.UserContactInfoDao;
import com.lcw.one.notify.channel.base.SmsService;
import com.lcw.one.notify.sender.IMessageSender;
import com.lcw.one.user.entity.UserContactInfoEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SmsMessageSender implements IMessageSender {

    private static Logger logger = LoggerFactory.getLogger(SmsMessageSender.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserContactInfoDao userContactInfoDao;

    @Override
    public void send(List<String> userIdList, MsgMessageEO msgMessageEO) {
        List<UserContactInfoEO> userContactInfoList = userContactInfoDao.listUserContractInfo(userIdList);
        for (UserContactInfoEO userContactInfoEO : userContactInfoList) {
            executorService.execute(new Thread(() -> smsService.sendSms(userContactInfoEO.getMobile(), msgMessageEO.getContent())));
        }
    }
}
