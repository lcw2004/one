package com.lcw.one.msg.service;

import com.lcw.one.msg.entity.MsgTemplateEO;
import com.lcw.one.msg.dao.MsgTemplateEODao;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgTemplateEOService extends CrudService<MsgTemplateEODao, MsgTemplateEO, String> {

    @Autowired
    private MsgTemplateChannelEOService msgTemplateChannelEOService;

    public PageInfo<MsgTemplateEO> page(PageInfo pageInfo, String likeName, String type) {
        return dao.page(pageInfo, likeName, type);
    }

    @Override
    public MsgTemplateEO save(MsgTemplateEO entity) {
        MsgTemplateEO msgTemplateEO = super.save(entity);
        msgTemplateChannelEOService.save(entity.getChannelIdList(), entity.getTemplateId());
        return msgTemplateEO;
    }

    @Override
    public MsgTemplateEO update(MsgTemplateEO entity) {
        msgTemplateChannelEOService.deleteByTemplateId(entity.getTemplateId());
        msgTemplateChannelEOService.save(entity.getChannelIdList(), entity.getTemplateId());
        return super.update(entity);
    }

    @Override
    public MsgTemplateEO get(String s) {
        MsgTemplateEO msgTemplateEO = super.get(s);
        msgTemplateEO.setChannelIdList(msgTemplateChannelEOService.listChannelIdByTemplateId(s));
        return msgTemplateEO;
    }
}