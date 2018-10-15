package com.lcw.one.util.echart;

import com.lcw.one.util.utils.CollectionUtils;

import java.util.*;

public class EChatData<T extends Number> {

    /**
     * 统计项列表
     */
    private Collection<String> titleList;

    /**
     * 横坐标列表
     */
    private Collection<String> xAxisList;

    /**
     * 数据列表，第一个键为统计项列表中的值，第二个键为横坐标列表的值
     */
    private Map<String, Map<String, T>> data = new HashMap<>();

    public Collection<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(Collection<String> titleList) {
        this.titleList = titleList;
    }

    public Collection<String> getxAxisList() {
        return xAxisList;
    }

    public void setxAxisList(Collection<String> xAxisList) {
        this.xAxisList = xAxisList;
    }

    public Map<String, Map<String, T>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, T>> data) {
        this.data = data;
    }

    private Map<String, T> getSubMap(String title) {
        Map<String, T> map;
        if (this.getData().containsKey(title)) {
            map = this.getData().get(title);
        } else {
            map = new HashMap<>();
            this.getData().put(title, map);
        }
        return map;
    }

    public void put(String title, String xAxis, T value) {
        getSubMap(title).put(xAxis, value);
    }

    public void put(String title, Map<String, T> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        for (String key : map.keySet()) {
            this.put(title, key, map.get(key));
        }
    }

}
