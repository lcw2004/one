package com.lcw.one.user.event;

import com.lcw.one.notify.service.IMessageSenderService;
import com.lcw.one.redis.bean.EventBean;
import com.lcw.one.redis.event.hanlder.BaseEventHandler;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.StringUtils;
import @{eventClass};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @{eventName}事件
 */
@Component
public class @{eventCode}EventHandler extends BaseEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(@{eventCode}EventHandler.class);

    @Autowired
    private IMessageSenderService iMessageSenderService;

    @Override
    public String getEventCode() {
        return @{eventEnum}.@{eventCodeBig}.getEventCode();
    }

    @Override
    public void handler(EventBean eventBean) {
        String projectId = (String) eventBean.getEventBody();
        if(StringUtils.isEmpty(projectId)) {
            throw new OneBaseException("项目ID不能为空");
        }


        Map<String, Object> params = eventBean.getEventParams();
        iMessageSenderService.sendMessage("", "30001", params);
    }
}
