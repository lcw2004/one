package com.lcw.one.msg.service;

import com.lcw.one.msg.entity.MsgChannelEO;
import com.lcw.one.msg.entity.MsgTemplateChannelEO;
import com.lcw.one.msg.dao.MsgTemplateChannelEODao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgTemplateChannelEOService extends CrudService<MsgTemplateChannelEODao, MsgTemplateChannelEO, String> {


    public List<String> listChannelIdByTemplateId(String templateId) {
        return dao.listChannelIdByTemplateId(templateId);
    }

    public List<MsgChannelEO> listChannelByTemplateId(String templateId) {
        return dao.listChannelByTemplateId(templateId);
    }

    public void deleteByTemplateId(String templateId) {
        dao.deleteByTemplateId(templateId);
    }

    public void save(List<String> channelIdList, String templateId) {
        for(String channelId: channelIdList) {
            MsgTemplateChannelEO msgTemplateChannelEO = new MsgTemplateChannelEO();
            msgTemplateChannelEO.setChannelId(channelId);
            msgTemplateChannelEO.setTemplateId(templateId);
            this.save(msgTemplateChannelEO);
        }
    }

}