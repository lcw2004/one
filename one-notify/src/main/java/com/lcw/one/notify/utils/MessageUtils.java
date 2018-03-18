package com.lcw.one.notify.utils;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{\\{(.*?)\\}\\}");
    private static final String PLACEHOLDER_START = "{{";
    private static final String PLACEHOLDER_END = "}}";

    /**
     * 将占位符填充到消息体中
     *
     * @param messageBody         消息体
     * @param placeHolderValueMap 占位符列表
     * @return
     */
    public static String fillMessageTemplate(String messageBody, Map<String, Object> placeHolderValueMap) {
        List<String> placeholderList = findPlaceHolderList(messageBody);
        validatePlaceHolder(placeholderList, placeHolderValueMap);
        if (CollectionUtils.isNotEmpty(placeholderList)) {
            for (String placeholder : placeholderList) {
                messageBody = messageBody.replace(PLACEHOLDER_START + placeholder + PLACEHOLDER_END, String.valueOf(placeHolderValueMap.get(placeholder)));
            }
        }
        return messageBody;
    }

    /**
     * 查询一个字符串里面的占位符
     *
     * @param message
     * @return
     */
    private static List<String> findPlaceHolderList(String message) {
        if (StringUtils.isEmpty(message)) {
            return null;
        }
        List<String> placeholderList = new ArrayList<>();
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(message);
        while (!matcher.hitEnd()) {
            matcher.find();
            if (!matcher.hitEnd()) {
                placeholderList.add(matcher.group().replace(PLACEHOLDER_START, " ").replace("}}", " ").trim());
            }
        }
        return placeholderList;
    }


    /**
     * 验证占位符
     *
     * @param placeholderList
     * @param placeHolderValueMap
     */
    private static void validatePlaceHolder(List<String> placeholderList, Map<String, Object> placeHolderValueMap) {
        // 无占位符需要填充
        if (placeholderList.size() == 0) {
            return;
        }
        if (CollectionUtils.isEmpty(placeHolderValueMap)) {
            throw new OneBaseException("需要占位符[" + StringUtils.listToString(placeholderList) + "]");
        }

        for (String placeHolder : placeholderList) {
            if (!placeHolderValueMap.containsKey(placeHolder)) {
                throw new OneBaseException("需要占位符[" + placeHolder + "]");
            }
        }
    }

}
