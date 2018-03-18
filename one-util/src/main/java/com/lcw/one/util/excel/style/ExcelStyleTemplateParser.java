package com.lcw.one.util.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel 样式模板解析器
 */
public class ExcelStyleTemplateParser {

    private Workbook modalWorkBook;
    private Sheet sheet;

    /**
     * 解析Excel文档，并获取该模板中的样式
     * @param is 模板文件
     * @return
     * @throws IOException
     */
    public Map<ExcelCellStyleEnum, CellStyle> loadCellStyle(InputStream is) throws IOException {
        parseExcel(is);

        Map<ExcelCellStyleEnum, CellStyle> cellStyleMap = new HashMap<>();

        // 枚举对应的样式
        for (ExcelCellStyleEnum styleEnum : ExcelCellStyleEnum.values()) {
            cellStyleMap.put(styleEnum, getCellStyle(styleEnum.getRowNum(), 0));
        }

        return cellStyleMap;
    }

    private void parseExcel(InputStream is) throws IOException {
        this.modalWorkBook = new XSSFWorkbook(is);
        this.sheet = this.modalWorkBook.getSheetAt(0);
    }

    private CellStyle getCellStyle(int rowNum, int cellNum) {
        return sheet.getRow(rowNum).getCell(cellNum).getCellStyle();
    }

}
