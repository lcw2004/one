package com.lcw.one.util.bean;

/**
 * 系统字典
 */
public class SysDict {

    private String label;
    private String value;

    public SysDict() {
    }

    public SysDict(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SysDict{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
