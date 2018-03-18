package com.lcw.one.util.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板样式，每一个Workbook单独存一份，供单个Excel使用
 */
public class TemplateExcelStyle implements IExcelStyle {

    private Workbook workbook;
    private Map<ExcelCellStyleEnum, CellStyle> cellStyleMap;

    public TemplateExcelStyle(Workbook workbook) {
        this.workbook = workbook;
        this.cellStyleMap = new HashMap<>();
    }

    private CellStyle cloneCellStyle(ExcelCellStyleEnum cellStyleEnum) {
        CellStyle style;
        if (!cellStyleMap.containsKey(cellStyleEnum)) {
            // 如果没有缓存，从系统静态缓存中clone一份
            style = workbook.createCellStyle();
            style.cloneStyleFrom(ExcelStyleTemplate.getCellStyle(cellStyleEnum));
            cellStyleMap.put(cellStyleEnum, style);
        } else {
            // 如果缓存了，直接返回
            style = cellStyleMap.get(cellStyleEnum);
        }
        return style;
    }

    @Override
    public CellStyle getStyle(ExcelCellStyleEnum cellStyleEnum) {
        return cloneCellStyle(cellStyleEnum);
    }

    @Override
    public CellStyle getTitleStyle() {
        return getStyle(ExcelCellStyleEnum.TITLE);
    }

    @Override
    public CellStyle getHeaderStyle() {
        return getStyle(ExcelCellStyleEnum.HEADER);
    }

    @Override
    public CellStyle getNormalStyle() {
        return getStyle(ExcelCellStyleEnum.NORMAL);
    }

    @Override
    public CellStyle getSuccessStyle() {
        return getStyle(ExcelCellStyleEnum.SUCCESS);
    }

    @Override
    public CellStyle getWarningStyle() {
        return getStyle(ExcelCellStyleEnum.WARNING);
    }

    @Override
    public CellStyle getDangerStyle() {
        return getStyle(ExcelCellStyleEnum.DANGER);
    }

    @Override
    public CellStyle getSuccessTextStyle() {
        return getStyle(ExcelCellStyleEnum.SUCCESS_TEXT);
    }

    @Override
    public CellStyle getWarningTextStyle() {
        return getStyle(ExcelCellStyleEnum.WARNING_TEXT);
    }

    @Override
    public CellStyle getDangerTextStyle() {
        return getStyle(ExcelCellStyleEnum.DANGER_TEXT);
    }

    @Override
    public CellStyle getDollarStyle() {
        return getStyle(ExcelCellStyleEnum.DOLLAR);
    }

    @Override
    public CellStyle getRateStyle() {
        return getStyle(ExcelCellStyleEnum.RATE);
    }
}
