package com.lcw.one.bidding.entity;

import javax.persistence.*;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_project_expert")
@IdClass(BidProjectExpertEOPK.class)
public class BidProjectExpertEO {
    private String projectId;
    private String userId;
    private Integer isSignIn;
    private Integer isLeader;

    @Id
    @Column(name = "project_id")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "is_sign_in")
    public Integer getIsSignIn() {
        return isSignIn;
    }

    public void setIsSignIn(Integer isSignIn) {
        this.isSignIn = isSignIn;
    }

    @Basic
    @Column(name = "is_leader")
    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidProjectExpertEO that = (BidProjectExpertEO) o;

        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (isSignIn != null ? !isSignIn.equals(that.isSignIn) : that.isSignIn != null) return false;
        if (isLeader != null ? !isLeader.equals(that.isLeader) : that.isLeader != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (isSignIn != null ? isSignIn.hashCode() : 0);
        result = 31 * result + (isLeader != null ? isLeader.hashCode() : 0);
        return result;
    }
}
