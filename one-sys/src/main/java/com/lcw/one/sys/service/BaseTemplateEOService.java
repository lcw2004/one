package com.lcw.one.sys.service;

import com.lcw.one.baseInfo.entity.BaseTemplateEO;
import com.lcw.one.baseInfo.entity.BaseTemplateTypeEO;
import com.lcw.one.sys.dao.BaseTemplateEODao;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class BaseTemplateEOService extends CrudService<BaseTemplateEODao, BaseTemplateEO, String> {

    @Autowired
    private BaseTemplateTypeEOService baseTemplateTypeEOService;

    @Autowired
    private RedisUtil redisUtil;

    public List<BaseTemplateEO> list(String templateTypeCode) {
        return dao.list(templateTypeCode);
    }

    public void cacheTemplate() {
        List<BaseTemplateTypeEO> baseTemplateTypeEOList = baseTemplateTypeEOService.findAll();
        for (BaseTemplateTypeEO baseTemplateTypeEO: baseTemplateTypeEOList) {
            BaseTemplateEO baseTemplateEO = getDefaultTemplate(baseTemplateTypeEO.getCode());
            if (baseTemplateEO != null) {
                redisUtil.set(BaseTemplateUtils.TEMPLATE_PREFIX + baseTemplateEO.getCode(), baseTemplateEO.getContent());
            }
        }
    }

    /**
     * 获取默认配置
     *
     * @param templateTypeCode
     * @return
     */
    public BaseTemplateEO getDefaultTemplate(String templateTypeCode) {
        List<BaseTemplateEO> templateEOList = list(templateTypeCode);

        if (CollectionUtils.isEmpty(templateEOList)) {
            return null;
        }

        BaseTemplateEO defaultTemplate = null;
        for (BaseTemplateEO template : templateEOList) {
            if (template.getIsDefault() == 1) {
                defaultTemplate = template;
                break;
            }
        }

        if (defaultTemplate == null) {
            defaultTemplate = templateEOList.get(0);
        }
        return defaultTemplate;
    }

    @Override
    public BaseTemplateEO save(BaseTemplateEO entity) {
        entity.setTemplateId(UUID.randomUUID10());

        long count = dao.count(entity.getCode());
        if (count == 0) {
            entity.setIsDefault(1);
        }

        super.save(entity);
        return entity;
    }

    public String fillTemplateByCode(String templateTypeCode, Map<String, Object> placeholders) {
        BaseTemplateEO templateEO = getDefaultTemplate(templateTypeCode);
        if (templateEO == null) {
            throw new OneBaseException("未配置模板");
        }
        return fillTemplateByTemplateId(templateEO.getTemplateId(), placeholders);
    }

    public String fillTemplateByTemplateId(String templateId, Map<String, Object> placeholders) {
        logger.info("fill template, templateId[" + templateId + "], placeholders[" + GsonUtil.toJson(placeholders) + "]");

        String result = "";
        try {
            BaseTemplateEO templateEO = get(templateId);
            String template = templateEO.getContent();
            result = BeetlUtils.fill(template, placeholders);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("fill template result:" + result);
        return result;
    }
}
