package com.lcw.one.redis.event.hanlder;


import com.lcw.one.redis.bean.EventBean;

public interface EventHandler {

    /**
     * 处理消息
     * @param eventBean
     */
    void handler(EventBean eventBean);
}
