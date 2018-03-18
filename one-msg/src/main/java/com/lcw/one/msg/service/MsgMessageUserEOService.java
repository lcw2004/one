package com.lcw.one.msg.service;

import com.lcw.one.msg.bean.MsgMessageStatusEnum;
import com.lcw.one.msg.entity.MsgMessageUserEO;
import com.lcw.one.msg.dao.MsgMessageUserEODao;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MsgMessageUserEOService extends CrudService<MsgMessageUserEODao, MsgMessageUserEO, String> {

    public void save(List<String> userIdList, String messageId) {
        if (CollectionUtils.isNotEmpty(userIdList)) {
            List<MsgMessageUserEO> msgMessageUserEOList = new ArrayList<>();
            for (String userId : userIdList) {
                MsgMessageUserEO msgMessageUserEO = new MsgMessageUserEO();
                msgMessageUserEO.setUserId(userId);
                msgMessageUserEO.setMessageId(messageId);
                msgMessageUserEO.setStatus(MsgMessageStatusEnum.UN_READ.getValue());
                msgMessageUserEOList.add(msgMessageUserEO);
            }
            this.save(msgMessageUserEOList);
        }
    }

}