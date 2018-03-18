package com.lcw.one.util.excel.style;

/**
 * Excel模板枚举
 */
public enum ExcelCellStyleEnum {

    // 标题
    TITLE(0),

    // 表格头部
    HEADER(1),

    // 正文内容
    NORMAL(2),

    // 成功的样式
    SUCCESS(3),

    // 警告的样式
    WARNING(4),

    // 错误的样式
    DANGER(5),

    // 成功文本的样式
    SUCCESS_TEXT(6),

    // 警告文本的样式
    WARNING_TEXT(7),

    // 错误文本的样式
    DANGER_TEXT(8),

    // 货币
    DOLLAR(9),

    // 百分比
    RATE(10);

    /**
     * 该样式在Excel模板中的序号
     */
    private int rowNum;

    ExcelCellStyleEnum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getRowNum() {
        return rowNum;
    }
}
