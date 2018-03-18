package com.lcw.one.util.persistence;

public enum DataScopeEnum {
    ALL(1, "所有数据"),
    COMPANY_AND_CHILD(2, "所在公司及以下数据"),
    COMPANY(3, "所在公司数据"),
    DEPARTMENT_AND_CHILD(4, "所在部门及以下数据"),
    DEPARTMENT(5, "所在部门数据"),
    SELF(8, "仅本人数据"),
    BY_DETAIL(9, "按明细设置"),;

    private Integer value;
    private String label;

    DataScopeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static DataScopeEnum get(Integer value) {
        DataScopeEnum[] types = DataScopeEnum.values();

        for (DataScopeEnum type : types) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        return null;
    }

}
