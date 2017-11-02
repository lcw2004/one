package com.lcw.one.workflow.entity;

import javax.persistence.*;

@Entity
@Table(name = "flow_task_info")
@IdClass(FlowTaskInfoEOPK.class)
public class FlowTaskInfoEO {
    private String processKey;
    private String taskKey;
    private String bindRole;
    private String bindForm;
    private String taskName;
    private Integer rankNum;
    private Integer valid;
    private String taskDesc;

    private Integer x;
    private Integer y;
    private Integer height;
    private Integer width;


    @Id
    @Column(name = "process_key")
    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String pkProcessKey) {
        this.processKey = pkProcessKey;
    }

    @Id
    @Column(name = "task_key")
    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String pkTaskKey) {
        this.taskKey = pkTaskKey;
    }

    @Basic
    @Column(name = "bind_role")
    public String getBindRole() {
        return bindRole;
    }

    public void setBindRole(String bindRole) {
        this.bindRole = bindRole;
    }

    @Basic
    @Column(name = "bind_form")
    public String getBindForm() {
        return bindForm;
    }

    public void setBindForm(String bindForm) {
        this.bindForm = bindForm;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "rank_num")
    public Integer getRankNum() {
        return rankNum;
    }

    public void setRankNum(Integer rankNum) {
        this.rankNum = rankNum;
    }

    @Basic
    @Column(name = "valid")
    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "task_desc")
    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Basic
    @Column(name = "x")
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y")
    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Basic
    @Column(name = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Basic
    @Column(name = "width")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
