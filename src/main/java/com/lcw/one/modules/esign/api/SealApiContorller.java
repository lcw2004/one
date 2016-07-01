package com.lcw.one.modules.esign.api;

import com.lcw.one.modules.esign.api.beal.ESignSealBean;
import com.lcw.one.modules.esign.api.beal.MessageBean;
import com.lcw.one.modules.esign.entity.Seal;
import com.lcw.one.modules.esign.entity.SealSignLog;
import com.lcw.one.modules.esign.service.CertificateService;
import com.lcw.one.modules.esign.service.SealService;
import com.lcw.one.modules.esign.service.SealSignLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/api/seal")
public class SealApiContorller {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private SealService sealService;

    @Autowired
    private SealSignLogService sealSignLogService;


    @RequestMapping(value = "token", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    MessageBean getSealByToken(String sealToken) {
        try {
            Seal seal = sealService.findByToken(sealToken);

            if(seal == null) {
                return new MessageBean("002", String.format("Token[%s]对应的印章不存在", sealToken));
            }

            if(seal.getSignCount() == null || seal.getSignCount() <= 0) {
                return new MessageBean("003", String.format("印章使用次数已经用完", sealToken));
            }

            MessageBean messageBean = new MessageBean(MessageBean.CODE_SUCCESS);
            messageBean.setBody(new ESignSealBean(seal));
            return messageBean;
        } catch (Exception e) {
            e.getMessage();
            return new MessageBean("001", e.getMessage());
        }
    }

    @RequestMapping(value = "log" ,method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    MessageBean saveSealSignLog(HttpServletRequest request, @RequestBody SealSignLog sealSignLog) {
        try {
            sealSignLog.setIp(request.getRemoteAddr());
            sealSignLog.setSignTime(new Date());

            sealSignLogService.save(sealSignLog);

            String sealId = sealSignLog.getSealId();
            Seal seal = sealService.get(sealId);
            if(seal.getSignCount() != null && seal.getSignCount() > 0) {
                seal.setSignCount(seal.getSignCount() - 1);
            }
            sealService.save(seal);

            return MessageBean.SUCCESS;
        } catch (Exception e) {
            e.getMessage();
            return new MessageBean("001", e.getMessage());
        }
    }

}
