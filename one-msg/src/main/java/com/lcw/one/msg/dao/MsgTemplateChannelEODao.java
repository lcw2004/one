package com.lcw.one.msg.dao;

import com.lcw.one.msg.entity.MsgChannelEO;
import com.lcw.one.msg.entity.MsgTemplateChannelEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MsgTemplateChannelEODao extends BaseRepositoryImpl<MsgTemplateChannelEO, String> {

    @Autowired
    public MsgTemplateChannelEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(MsgTemplateChannelEO.class, entityManager), entityManager);
    }

    public List<String> listChannelIdByTemplateId(String templateId) {
        return executeList("select channelId from MsgTemplateChannelEO where templateId = ?1", templateId);
    }

    public List<MsgChannelEO> listChannelByTemplateId(String templateId) {
        return executeList("select c from MsgChannelEO c, MsgTemplateChannelEO tc where c.channelId = tc.channelId and tc.templateId = ?1", templateId);
    }

    public void deleteByTemplateId(String templateId) {
        executeUpdate("delete from MsgTemplateChannelEO where templateId = ?1", templateId);
    }
}