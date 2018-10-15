package com.lcw.one.util.echart;

import java.util.List;

public class EChart<T> {

    private List<String> xAxis;

    private List<T> series;

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<T> getSeries() {
        return series;
    }

    public void setSeries(List<T> series) {
        this.series = series;
    }
}
