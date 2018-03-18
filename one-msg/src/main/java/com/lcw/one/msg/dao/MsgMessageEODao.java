package com.lcw.one.msg.dao;

import com.lcw.one.msg.bean.MsgMessageStatusEnum;
import com.lcw.one.msg.bean.MsgMessageVO;
import com.lcw.one.msg.entity.MsgMessageEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class MsgMessageEODao extends BaseRepositoryImpl<MsgMessageEO, String> {

    @Autowired
    public MsgMessageEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(MsgMessageEO.class, entityManager), entityManager);
    }

    public Long totalCount (String userId) {
       return executeGet("select count (1) from MsgMessageUserEO where userId = ?1 and status != ?2", userId, MsgMessageStatusEnum.DELETED.getValue());
    }

    public Long totalUnReadCount (String userId) {
        return executeGet("select count (1) from MsgMessageUserEO where userId = ?1 and status = ?2", userId, MsgMessageStatusEnum.UN_READ.getValue());
    }

    public PageInfo<MsgMessageVO> pageByUserId(PageInfo pageInfo, String userId, String status) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" select new com.lcw.one.msg.bean.MsgMessageVO(m, mu.status) ");
        hql.append(" from MsgMessageEO m, MsgMessageUserEO mu where m.messageId = mu.messageId ");

        // 用户ID
        hql.append(" and mu.userId = :userId");
        paramMap.put("userId", userId);

        // 消息状态
        if (StringUtils.isNotEmpty(status)) {
            hql.append(" and mu.status = ").append(Integer.valueOf(status));
        } else {
            hql.append(" and mu.status != ").append(MsgMessageStatusEnum.DELETED.getValue());
        }


        // 排序
        hql.append(" order by m.sendTime desc");

        return executePage(pageInfo, hql.toString(), paramMap);
    }

    public void updateStatus(String userId, Integer newStatus) {
        executeUpdate("update MsgMessageUserEO set status = ?1 where userId = ?2 and status != -1", newStatus, userId);
    }

    public void markAllAsRead(String userId) {
        updateStatus(userId, MsgMessageStatusEnum.HAS_READ.getValue());
    }

    public void markAllAsDelete(String userId) {
        updateStatus(userId, MsgMessageStatusEnum.DELETED.getValue());
    }

}