package com.lcw.one.notify.service.sender;

import com.lcw.one.notify.service.IMessageSender;
import com.lcw.one.notify.service.sender.EmailMessageSender;
import com.lcw.one.notify.service.sender.SmsMessageSender;
import com.lcw.one.notify.service.sender.SystemMessageSender;
import com.lcw.one.notify.service.sender.WeixinMessageSender;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.SpringContextHolder;

public class IMessageSenderFactory {

    public static IMessageSender instance(String msgChannelId) {
        if (msgChannelId == null) {
            throw new OneBaseException("渠道编号不能为空");
        }

        Class clazz = null;
        if ("1".equals(msgChannelId)) {
            clazz = SystemMessageSender.class;
        } else if ("2".equals(msgChannelId)) {
            clazz = SystemMessageSender.class;
        } else if ("3".equals(msgChannelId)) {
            clazz = SmsMessageSender.class;
        } else if ("4".equals(msgChannelId)) {
            clazz = EmailMessageSender.class;
        } else if ("5".equals(msgChannelId)) {
            clazz = WeixinMessageSender.class;
        }
        if (clazz == null) {
            throw new OneBaseException("找不到渠道[" + msgChannelId + "]对应的处理器类");
        }

        IMessageSender iMessageSender = (IMessageSender) SpringContextHolder.getBean(clazz);
        if (iMessageSender == null) {
            throw new OneBaseException("找不到渠道[" + msgChannelId + "]对应的处理器类");
        }
        return iMessageSender;
    }

}
