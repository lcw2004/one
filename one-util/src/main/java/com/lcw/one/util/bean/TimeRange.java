package com.lcw.one.util.bean;

import com.lcw.one.util.utils.DateUtils;

import java.util.Date;

/**
 * 时间区间
 */
public class TimeRange {

    private Date startTime;
    private Date endTime;

    public TimeRange() {
    }

    public TimeRange(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "startTime=" + DateUtils.dateToString(startTime) +
                ", endTime=" + DateUtils.dateToString(endTime) +
                '}';
    }

}
