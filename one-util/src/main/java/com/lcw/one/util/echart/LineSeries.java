package com.lcw.one.util.echart;

import java.util.List;

public class LineSeries<T> {

    private String name;
    private String type;
    private List<T> data;

    public LineSeries(String name, String type, List<T> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
