package com.lcw.one.workflow.dao;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.workflow.bean.constant.ProcessStateEOEnum;
import com.lcw.one.workflow.entity.FlowInfoEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlowInfoEODao extends BaseRepositoryImpl<FlowInfoEO, String> {

    @Autowired
    public FlowInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowInfoEO.class, entityManager), entityManager);
    }

    public void save(List<FlowInfoEO> processStateList) {
        if (CollectionUtils.isEmpty(processStateList)) {
            return;
        }

        for (FlowInfoEO flowInfoEO : processStateList) {
            FlowInfoEO flowInfoEOInDb = findOne(flowInfoEO.getProcessKey());
            if (flowInfoEOInDb != null) {
                flowInfoEO.setProcessState(ProcessStateEOEnum.UPDATED.getValue());
            } else {
                flowInfoEO.setProcessState(ProcessStateEOEnum.ADDED.getValue());
            }
            save(flowInfoEO);
        }
    }

    public PageInfo<FlowInfoEO> page(PageInfo pageInfo, String likeName) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        sql.append("from FlowInfoEO where 1=1 ");

        if(StringUtils.isNotEmpty(likeName)) {
            sql.append(" and processKey like :likeName or processName like :likeName");
            params.put("likeName", "%" + likeName + "%");
        }
        sql.append(" order by lastUpdateTime desc");
        return super.page(pageInfo, sql.toString(), params);
    }

}