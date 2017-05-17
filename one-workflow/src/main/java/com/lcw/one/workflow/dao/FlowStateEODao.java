package com.lcw.one.workflow.dao;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.workflow.bean.constant.ProcessStateEOEnum;
import com.lcw.one.workflow.entity.FlowStateEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

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

    public PageInfo<FlowStateEO> page(PageInfo pageInfo) {
        return page(pageInfo, "from FlowStateEO");
    }

}