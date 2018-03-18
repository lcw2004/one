package com.lcw.one.util.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel导出样式抽象类（考虑到各个项目会更换样式，再抽象了一层）
 */
public interface IExcelStyle {

    CellStyle getStyle(ExcelCellStyleEnum cellStyleEnum);

    CellStyle getTitleStyle();

    CellStyle getHeaderStyle();

    CellStyle getNormalStyle();

    CellStyle getSuccessStyle();

    CellStyle getWarningStyle();

    CellStyle getDangerStyle();

    CellStyle getSuccessTextStyle();

    CellStyle getWarningTextStyle();

    CellStyle getDangerTextStyle();

    CellStyle getDollarStyle();

    CellStyle getRateStyle();
}
