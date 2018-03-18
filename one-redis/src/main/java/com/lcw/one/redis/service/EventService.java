package com.lcw.one.redis.service;

import com.lcw.one.redis.bean.EventBean;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class EventService<T> {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private static final String ONE_EVENT_CHANNEL = "OneEvent";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysEventEOService sysEventEOService;

    public void createEvent(String eventCode, String eventName, T eventBody) {
        createEvent(eventCode, eventName, eventBody, null);
    }

    public void createEvent(String eventCode, String eventName, T eventBody, Map<String, Object> eventParams) {
        EventBean eventBean = new EventBean();
        eventBean.setEventCode(eventCode);
        eventBean.setEventName(eventName);
        eventBean.setEventBody(eventBody);
        eventBean.setEventParams(eventParams);
        createEvent(eventBean);
    }

    public void createEvent(EventBean eventBean) {
        eventBean.setCreateTime(new Date());
        eventBean.setEventId(UUID.randomUUID());

        // 存数据库做备份，立即执行，否则事件接收的时候还没执行会弄乱数据
        sysEventEOService.save(eventBean);
        sysEventEOService.flush();

        // 调用Redis发送事件
        String json = GsonUtil.toJson(eventBean);
        logger.info("Send Event Message [{}]", json);
        stringRedisTemplate.convertAndSend(ONE_EVENT_CHANNEL, json);
    }

}
