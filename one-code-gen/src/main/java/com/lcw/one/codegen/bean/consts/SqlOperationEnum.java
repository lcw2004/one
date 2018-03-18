package com.lcw.one.codegen.bean.consts;

public enum SqlOperationEnum {

    EQUAL("1", "="),
    NOT_EQUAL("2", "!="),
    GREAT("3", ">"),
    GREAT_EQUAL("4", ">="),
    LESS("5", "<"),
    LESS_EQUAL("6", "<="),
    BETWEEN("7", ""),
    LIKE("8", "like"),
    LEFT_LIKE("9", "like"),
    RIGHT_LIKE("10", "like"),
    IN("11", "in");

    private String queryType;
    private String operation;

    SqlOperationEnum(String queryType, String operation) {
        this.queryType = queryType;
        this.operation = operation;
    }

    public static SqlOperationEnum get(String value) {
        SqlOperationEnum[] types = SqlOperationEnum.values();

        for (SqlOperationEnum type : types) {
            if (value.equals(type.getQueryType())) {
                return type;
            }
        }
        return null;
    }

    public String getQueryType() {
        return queryType;
    }

    public String getOperation() {
        return operation;
    }
}
