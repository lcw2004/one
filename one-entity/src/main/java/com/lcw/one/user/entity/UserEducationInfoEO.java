package com.lcw.one.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Arrays;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_education_info")
public class UserEducationInfoEO {
    private String userId;
    private String lastEducation;
    private String lastSchool;
    private String studyProfession;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date graduationTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workStartTime;
    private String workProfession;
    private byte[] workResume;
    private byte[] graduationCertificate;
    private Integer politicalStatus;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "last_education")
    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    @Basic
    @Column(name = "last_school")
    public String getLastSchool() {
        return lastSchool;
    }

    public void setLastSchool(String lastSchool) {
        this.lastSchool = lastSchool;
    }

    @Basic
    @Column(name = "study_profession")
    public String getStudyProfession() {
        return studyProfession;
    }

    public void setStudyProfession(String studyProfession) {
        this.studyProfession = studyProfession;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Basic
    @Column(name = "graduation_time")
    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Basic
    @Column(name = "work_start_time")
    public Date getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    }

    @Basic
    @Column(name = "work_profession")
    public String getWorkProfession() {
        return workProfession;
    }

    public void setWorkProfession(String workProfession) {
        this.workProfession = workProfession;
    }

    @Basic
    @Column(name = "work_resume")
    public byte[] getWorkResume() {
        return workResume;
    }

    public void setWorkResume(byte[] workResume) {
        this.workResume = workResume;
    }

    @Basic
    @Column(name = "graduation_certificate")
    public byte[] getGraduationCertificate() {
        return graduationCertificate;
    }

    public void setGraduationCertificate(byte[] graduationCertificate) {
        this.graduationCertificate = graduationCertificate;
    }

    @Basic
    @Column(name = "political_status")
    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEducationInfoEO that = (UserEducationInfoEO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (lastEducation != null ? !lastEducation.equals(that.lastEducation) : that.lastEducation != null)
            return false;
        if (lastSchool != null ? !lastSchool.equals(that.lastSchool) : that.lastSchool != null) return false;
        if (studyProfession != null ? !studyProfession.equals(that.studyProfession) : that.studyProfession != null)
            return false;
        if (graduationTime != null ? !graduationTime.equals(that.graduationTime) : that.graduationTime != null)
            return false;
        if (workStartTime != null ? !workStartTime.equals(that.workStartTime) : that.workStartTime != null)
            return false;
        if (workProfession != null ? !workProfession.equals(that.workProfession) : that.workProfession != null)
            return false;
        if (!Arrays.equals(workResume, that.workResume)) return false;
        if (!Arrays.equals(graduationCertificate, that.graduationCertificate)) return false;
        if (politicalStatus != null ? !politicalStatus.equals(that.politicalStatus) : that.politicalStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (lastEducation != null ? lastEducation.hashCode() : 0);
        result = 31 * result + (lastSchool != null ? lastSchool.hashCode() : 0);
        result = 31 * result + (studyProfession != null ? studyProfession.hashCode() : 0);
        result = 31 * result + (graduationTime != null ? graduationTime.hashCode() : 0);
        result = 31 * result + (workStartTime != null ? workStartTime.hashCode() : 0);
        result = 31 * result + (workProfession != null ? workProfession.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(workResume);
        result = 31 * result + Arrays.hashCode(graduationCertificate);
        result = 31 * result + (politicalStatus != null ? politicalStatus.hashCode() : 0);
        return result;
    }
}
