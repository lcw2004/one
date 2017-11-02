package com.lcw.one.workflow.dao;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.workflow.bean.constant.ProcessStateEOEnum;
import com.lcw.one.workflow.entity.FlowStateEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlowStateEODao extends BaseRepositoryImpl<FlowStateEO, String> {

    @Autowired
    public FlowStateEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowStateEO.class, entityManager), entityManager);
    }

    public void save(List<FlowStateEO> processStateList) {
        if (CollectionUtils.isEmpty(processStateList)) {
            return;
        }

        for (FlowStateEO flowStateEO : processStateList) {
            FlowStateEO flowStateEOInDb = findOne(flowStateEO.getProcessKey());
            if (flowStateEOInDb != null) {
                flowStateEO.setProcessState(ProcessStateEOEnum.UPDATED.getValue());
            } else {
                flowStateEO.setProcessState(ProcessStateEOEnum.ADDED.getValue());
            }
            save(flowStateEO);
        }
    }

    public PageInfo<FlowStateEO> page(PageInfo pageInfo, String likeName) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        sql.append("from FlowStateEO where 1=1 ");

        if(StringUtils.isNotEmpty(likeName)) {
            sql.append(" and processKey like :likeName or processName like :likeName");
            params.put("likeName", "%" + likeName + "%");
        }
        return super.page(pageInfo, sql.toString(), params);
    }

}