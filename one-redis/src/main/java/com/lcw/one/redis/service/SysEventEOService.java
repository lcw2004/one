package com.lcw.one.redis.service;


import com.lcw.one.redis.bean.EventBean;
import com.lcw.one.redis.bean.constant.EventStatusEnum;
import com.lcw.one.redis.dao.SysEventEODao;
import com.lcw.one.sys.entity.SysEventEO;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.GsonUtil;
import org.springframework.stereotype.Service;

@Service
public class SysEventEOService extends CrudService<SysEventEODao, SysEventEO, String> {

    public void save(EventBean eventBean) {
        SysEventEO sysEventEO = new SysEventEO();
        sysEventEO.setEventId(eventBean.getEventId());
        sysEventEO.setEventCode(eventBean.getEventCode());
        sysEventEO.setEventName(eventBean.getEventName());
        if (CollectionUtils.isNotEmpty(eventBean.getEventParams())) {
            sysEventEO.setEventParam(GsonUtil.toJson(eventBean.getEventParams()));
        }
        if (eventBean.getEventBody() != null) {
            sysEventEO.setEventContent(GsonUtil.toJson(eventBean.getEventBody()));
        }
        sysEventEO.setCreateTime(eventBean.getCreateTime());
        sysEventEO.setStatus(EventStatusEnum.NOT_DONE.getValue());
        this.save(sysEventEO);
    }

    public void updateEventStatus(String eventId, Integer status, String exception) {
        dao.updateEventStatus(eventId, status, exception);
    }
}
