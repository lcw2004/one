package com.lcw.one.msg.service;

import com.lcw.one.msg.constant.MsgAnnounceStatusEnum;
import com.lcw.one.msg.entity.MsgAnnounceEO;
import com.lcw.one.msg.dao.MsgAnnounceEODao;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.UUID;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgAnnounceEOService extends CrudService<MsgAnnounceEODao, MsgAnnounceEO, String> {

    public PageInfo<MsgAnnounceEO> page(PageInfo pageInfo, String likeName) {
        return dao.page(pageInfo, likeName);
    }

    public PageInfo<MsgAnnounceEO> pageForRead(PageInfo pageInfo, LoginUser loginUser, String likeName) {
        return dao.pageForRead(pageInfo, loginUser, likeName);
    }

    @Override
    public MsgAnnounceEO save(MsgAnnounceEO entity) {
        entity.setAnnounceId(UUID.randomUUID());
        entity.setCreateTime(new Date());
        entity.setIsTop(0);
        entity.setReadCount(0);
        entity.setStatus(MsgAnnounceStatusEnum.DRAFT.getValue());
        return super.save(entity);
    }

    public MsgAnnounceEO publish(MsgAnnounceEO entity) {
        if (StringUtils.isNotEmpty(entity.getAnnounceId())) {
            entity.setAnnounceId(UUID.randomUUID());
        }
        entity.setCreateTime(new Date());
        entity.setIsTop(0);
        entity.setReadCount(0);
        entity.setStatus(MsgAnnounceStatusEnum.STARTED.getValue());
        return super.save(entity);
    }

    public void increaseReadCount(String announceId) {
        dao.increaseReadCount(announceId);
    }

    public void updateStatus(String announceId, Integer newStatus) {
        dao.updateStatus(announceId, newStatus);
    }

    public void updateStatus(List<String> announceIdList, Integer newStatus) {
        dao.updateStatus(announceIdList, newStatus);
    }

    public void delete(List<String> announceIdList) {
        dao.updateStatus(announceIdList, MsgAnnounceStatusEnum.DELETED.getValue());
    }

    public void updateIsTop(String announceId, Integer isTop) {
        dao.updateIsTop(announceId, isTop);
    }
}