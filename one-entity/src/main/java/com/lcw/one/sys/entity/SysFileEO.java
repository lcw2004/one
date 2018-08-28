package com.lcw.one.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2017-04-16.
 * @author Licw
 */
@Entity
@Table(name = "sys_file")
public class SysFileEO {
    private String fileId;
    private String fileName;
    private String fileType;
    private String savePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;
    private String url;
    private String remark;
    private String userId;
    private String contentType;
    private Integer permissionType;

    @Id
    @Column(name = "file_id")
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "save_path")
    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "permission_type")
    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysFileEO sysFileEO = (SysFileEO) o;

        if (fileId != null ? !fileId.equals(sysFileEO.fileId) : sysFileEO.fileId != null) return false;
        if (fileName != null ? !fileName.equals(sysFileEO.fileName) : sysFileEO.fileName != null) return false;
        if (fileType != null ? !fileType.equals(sysFileEO.fileType) : sysFileEO.fileType != null) return false;
        if (savePath != null ? !savePath.equals(sysFileEO.savePath) : sysFileEO.savePath != null) return false;
        if (createTime != null ? !createTime.equals(sysFileEO.createTime) : sysFileEO.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(sysFileEO.lastUpdateTime) : sysFileEO.lastUpdateTime != null)
            return false;
        if (url != null ? !url.equals(sysFileEO.url) : sysFileEO.url != null) return false;
        if (remark != null ? !remark.equals(sysFileEO.remark) : sysFileEO.remark != null) return false;
        if (userId != null ? !userId.equals(sysFileEO.userId) : sysFileEO.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileId != null ? fileId.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (savePath != null ? savePath.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
