package com.lcw.one.util.utils;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BeetlUtils {

    public static String fill(String content, Map<String, Object> params) throws IOException {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate(content);

        for (String key : params.keySet()) {
            Object value = params.get(key);
            t.binding(key, value);
        }

        return t.render();
    }

    public static void main(String[] args) {
        String template = "<p>\n" +
                "\t<span style=\"font-size:18px;\"></span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:18px;\">一、项目/工程概要</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">1. 项目名称：</span><span style=\"font-size:16px;\">${projectName}</span>\n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">2. 项目编号：projectId</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">3. 采购类别：purchaseType.name</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:18px;\">二、投标资格要求</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">&nbsp; &nbsp;qualificationRemark</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:18px;\">三、时间和地点</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">1. 购买标书截止时间：buyEndTime</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">2. 投标截止时间：uploadEndTime</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">3. 开标时间：openTime</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">4. 标书费：人民币sellPrice元</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">5. 投标保证金：人民币bidPrice元</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">6. 开标地点：</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">四、联系人以及联系方式</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">1. 联系人：purchaseUser.name</span> \n" +
                "</p>\n" +
                "<p style=\"white-space:normal;\">\n" +
                "\t<span style=\"font-size:16px;\">2. 联系电话：purchaseUser.userContactInfo.phone</span> \n" +
                "</p>\n" +
                "<span style=\"display:none;\"></span> \n" +
                "<p>\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:16px;\"><span style=\"display:none;\"></span><span style=\"display:none;\"></span></span> \n" +
                "</p>";

        Map<String, Object> params =new HashMap<>();
        params.put("projectName", "11111111哈哈");

    }
}
