package com.lcw.one.msg.dao;

import com.lcw.one.msg.entity.MsgAnnounceEO;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MsgAnnounceEODao extends BaseRepositoryImpl<MsgAnnounceEO, String> {

    @Autowired
    public MsgAnnounceEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(MsgAnnounceEO.class, entityManager), entityManager);
    }

    public PageInfo<MsgAnnounceEO> page(PageInfo pageInfo, String likeName) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        hql.append(" from MsgAnnounceEO where 1=1 ");
        if (StringUtils.isNotEmpty(likeName)) {
            hql.append(" and title like :likeName");
            params.put("likeName", "%" + likeName + "%");
        }
        hql.append(" order by isTop desc, createTime desc ");

        return page(pageInfo, hql.toString(), params);
    }

    public PageInfo<MsgAnnounceEO> pageForRead(PageInfo pageInfo, LoginUser loginUser, String likeName) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        hql.append(" from MsgAnnounceEO where 1=1 ");
        if (StringUtils.isNotEmpty(likeName)) {
            hql.append(" and title like :likeName");
            params.put("likeName", "%" + likeName + "%");
        }

        hql.append(" order by isTop desc, createTime desc ");
        return page(pageInfo, hql.toString(), params);
    }

    public void increaseReadCount(String announceId) {
        executeUpdate("update MsgAnnounceEO set readCount = readCount + 1 where announceId = ?1", announceId);
    }

    public void updateStatus(String announceId, Integer newStatus) {
        executeUpdate("update MsgAnnounceEO set status = ?1 where announceId = ?2", newStatus, announceId);
    }

    public void updateStatus(List<String> announceIdList, Integer newStatus) {
        executeUpdate("update MsgAnnounceEO set status = ?1 where announceId in ?2", newStatus, announceIdList);
    }

    public void updateIsTop(String announceId, Integer isTop) {
        executeUpdate("update MsgAnnounceEO set isTop = ?1 where announceId = ?2", isTop, announceId);
    }

}