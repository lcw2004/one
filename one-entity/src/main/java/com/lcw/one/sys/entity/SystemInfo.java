package com.lcw.one.sys.entity;

public class SystemInfo {

    private String fullName;
    private String shortName;
    private String version;
    private String powerByName;
    private String powerByLink;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPowerByName() {
        return powerByName;
    }

    public void setPowerByName(String powerByName) {
        this.powerByName = powerByName;
    }

    public String getPowerByLink() {
        return powerByLink;
    }

    public void setPowerByLink(String powerByLink) {
        this.powerByLink = powerByLink;
    }
}
