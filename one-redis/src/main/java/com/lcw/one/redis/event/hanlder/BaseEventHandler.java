package com.lcw.one.redis.event.hanlder;


import com.lcw.one.redis.bean.EventBean;

/**
 * @version 2017-08-25.
 * @auth Licw
 */
public abstract class BaseEventHandler implements EventHandler {

    public BaseEventHandler() {
        Class clazz = this.getClass();
        EventHandlerRegistry.registerEventHandler(getEventCode(), clazz);
    }


    /**
     * 事件编码
     * @return
     */
    public abstract String getEventCode();

    /**
     * 事件处理程序
     * @param eventBean
     */
    @Override
    public abstract void handler(EventBean eventBean);
}
