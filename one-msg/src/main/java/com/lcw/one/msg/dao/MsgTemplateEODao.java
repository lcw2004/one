package com.lcw.one.msg.dao;

import com.lcw.one.msg.bean.MsgMessageStatusEnum;
import com.lcw.one.msg.entity.MsgTemplateEO;
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
public class MsgTemplateEODao extends BaseRepositoryImpl<MsgTemplateEO, String> {

    @Autowired
    public MsgTemplateEODao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(MsgTemplateEO.class, entityManager), entityManager);
    }

    public PageInfo<MsgTemplateEO> page(PageInfo pageInfo, String likeName, String type) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" from MsgTemplateEO m where 1=1 ");


        if (StringUtils.isNotEmpty(likeName)) {
            hql.append(" and ( templateId like :likeName or title like :likeName or content like :likeName )");
            paramMap.put("likeName", "%" + likeName + "%");
        }

        if (StringUtils.isNotEmpty(type)) {
            hql.append(" and msgType.msgTypeCode = :type");
            paramMap.put("type", type);
        }

        // 排序
        hql.append(" order by templateId asc");

        return executePage(pageInfo, hql.toString(), paramMap);
    }

    public boolean isExist(String templateId) {
        long count = executeGet("select count (1) from MsgTemplateEO where templateId = ?1", templateId);
        return count > 0;
    }

}