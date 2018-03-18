package com.lcw.one.util.excel;

import com.lcw.one.util.excel.rule.ExcelFieldRule;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.IOUtils;
import com.lcw.one.util.utils.NumberUtils;
import com.lcw.one.util.utils.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class POIUtils {

    private static final Logger logger = LoggerFactory.getLogger(POIUtils.class);

    /**
     * 将值转为对应的类型
     * @param inputValue
     * @param filedClass
     * @return
     */
    public static Object getValueAsClass(Object inputValue, Class filedClass) {
        Object value = null;

        if (StringUtils.isEmpty(String.valueOf(inputValue))) {
            return null;
        }

        if (filedClass == String.class) {
            if (NumberUtils.isNumber(inputValue)) {
                value = NumberUtils.numberToString(inputValue);
            } else {
                value = String.valueOf(inputValue);
            }
        } else if (filedClass == Integer.class) {
            value = Double.valueOf(inputValue.toString()).intValue();
        } else if (filedClass == Long.class) {
            value = Double.valueOf(inputValue.toString()).longValue();
        } else if (filedClass == Double.class) {
            value = Double.valueOf(inputValue.toString());
        } else if (filedClass == Float.class) {
            value = Float.valueOf(inputValue.toString());
        } else if (filedClass == Date.class) {
            value = DateUtil.getJavaDate((Double) inputValue);
        } else {
            value = null;
        }
        return value;
    }

    /**
     * 获取cell的值，并转为指定的class
     *
     * @param cell
     * @param filedClass
     * @return
     */
    public static Object getCellValueAsClass(Cell cell, Class filedClass) {
        Object cellValue = getCellValue(cell);
        if (cellValue == null) {
            return null;
        }

        Object value = null;
        try {
            value = getValueAsClass(cellValue, filedClass);
        } catch (Exception e) {
            logger.warn("Excel文档第{}行第{}列解析错误，异常：{}", cell.getRowIndex(), cell.getColumnIndex(), e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return value;
    }

    /**
     * 获取单元格的值
     *
     * @param cell 单元格
     * @return
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        Object val = null;
        try {
            CellType cellType = cell.getCellTypeEnum();
            switch (cellType) {
                case NUMERIC:
                    val = cell.getNumericCellValue();
                    break;
                case STRING:
                    val = cell.getStringCellValue();
                    break;
                case FORMULA:
                    val = cell.getCellFormula();
                    break;
                case BOOLEAN:
                    val = cell.getBooleanCellValue();
                    break;
                case ERROR:
                    val = cell.getErrorCellValue();
                    break;
                default:
                    val = "";
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return val;
    }

    /**
     * 关闭workbook
     *
     * @param workbook Excel文档
     */
    public static void closeWorkBooK(Workbook workbook) {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            // ignore
            // e.printStackTrace();
        }
    }

    /**
     * 将Excel文档写到OS流中
     *
     * @param workbook 文档
     * @param os       输出流
     */
    public static void write(Workbook workbook, OutputStream os) throws IOException {
        workbook.write(os);
    }

    /**
     * 将Excel文档转为字节数组
     *
     * @param workbook 文档
     */
    public static byte[] toBytes(Workbook workbook) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            write(workbook, baos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(baos);
        }
        return baos.toByteArray();
    }

    /**
     * 将文档设置为自适应大小
     *
     * @param sheet sheet
     */
    public static void autoSize(Sheet sheet) {
        autoSize(sheet, sheet.getLastRowNum());
    }

    /**
     * 将文档设置为按指定行自适应大小
     *
     * @param sheet  sheet
     * @param rowNum 行号
     */
    public static void autoSize(Sheet sheet, int rowNum) {
        Row row = sheet.getRow(rowNum);
        if (row != null) {
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                sheet.autoSizeColumn(i, true);
                if (sheet.getColumnWidth(i) == 0) {
                    sheet.setColumnWidth(i, 100);
                }
            }
        }
    }

    /**
     * 获取Cell，如果没有则创建新的
     *
     * @param row    行
     * @param colNum 列号
     * @return
     */
    public static Cell createCell(Row row, int colNum) {
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        return cell;
    }

    /**
     * 创建Row，如果没有则创建新的
     *
     * @param sheet  sheet
     * @param rowNum 行号
     * @return
     */
    public static Row createRow(Sheet sheet, int rowNum) {
        Row headerRow = sheet.getRow(rowNum);
        if (headerRow == null) {
            headerRow = sheet.createRow(rowNum);
        }
        return headerRow;
    }

    /**
     * 设置单元格的值
     *
     * @param row       row
     * @param colNum    列号
     * @param value     需要填充的值
     * @param cellStyle 样式
     */
    public static void setCellValue(Workbook workbook, Row row, int colNum, Object value, CellStyle cellStyle) {
        Cell cell = createCell(row, colNum);
        cell.setCellStyle(cellStyle);
        setCellValue(workbook, cell, value, cellStyle);
    }

    /**
     * 设置Cell的值
     *
     * @param cell 单元格
     * @param val  需要设置的值
     * @return
     */
    private static void setCellValue(Workbook workbook, Cell cell, Object val, CellStyle cellStyle) {
        try {
            if (val == null) {
                cell.setCellValue("");
                cell.setCellType(CellType.STRING);
            } else if (val instanceof String) {
                cell.setCellValue((String) val);
                cell.setCellType(CellType.STRING);
            } else if (val instanceof Integer) {
                cell.setCellValue(String.valueOf(val));
                cell.setCellType(CellType.STRING);
            } else if (val instanceof Long) {
                cell.setCellValue(String.valueOf(val));
                cell.setCellType(CellType.STRING);
            } else if (val instanceof Double) {
                cell.setCellValue(NumberUtils.formatDouble((Double) val));
                cell.setCellType(CellType.STRING);
            } else if (val instanceof Float) {
                cell.setCellValue(String.valueOf(val));
                cell.setCellType(CellType.STRING);
            } else if (val instanceof Date || val instanceof Timestamp) {
                cell.setCellValue(String.valueOf(DateUtils.dateToString((Date)val, DateUtils.yyyy_MM_dd_HH_mm_ss_CN)));
                cell.setCellType(CellType.STRING);
            } else {
                if (val.getClass() != Class.class) {
                    cell.setCellValue((String) val.getClass().getMethod("setValue", Object.class).invoke(null, val));
                } else {
                    cell.setCellValue((String) Class.forName(POIUtils.class.getClass().getName().replaceAll(POIUtils.class.getClass().getSimpleName(),
                            "fieldtype." + val.getClass().getSimpleName() + "Type")).getMethod("setValue", Object.class).invoke(null, val));
                }
            }
        } catch (Exception ex) {
            logger.error("Set cell value [" + cell.getRowIndex() + "," + cell.getColumnIndex() + "] error: " + ex.toString());
            if (val != null) {
                cell.setCellValue(val.toString());
            }
        }
        cell.setCellStyle(cellStyle);
    }

    /**
     * 填充空白值
     *
     * @param cellStyle  单元格样式
     * @param colNumFrom 起始列
     * @param colNumTo   终止列
     */
    public static void fillBlankValueInRow(Row row, CellStyle cellStyle, int colNumFrom, int colNumTo) {
        for (int i = colNumFrom; i <= colNumTo; i++) {
            Cell cell = createCell(row, i);
            cell.setCellValue("");
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 合并横向单元格
     *
     * @param sheet      sheet
     * @param cellStyle  单元格样式
     * @param rowNum     需要合并的行号
     * @param colNumFrom 起始列
     * @param colNumTo   终止列
     */
    public static void mergeCellRangeInRow(Sheet sheet, CellStyle cellStyle, int rowNum, int colNumFrom, int colNumTo) {
        Row row = createRow(sheet, rowNum);

        CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum, rowNum, colNumFrom, colNumTo);
        sheet.addMergedRegion(cellRangeAddress);

        for (int i = colNumFrom; i <= colNumTo; i++) {
            Cell cell = createCell(row, i);
            cell.setCellValue("");
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 合并纵向单元格
     *
     * @param sheet      sheet
     * @param cellStyle  单元格样式
     * @param colNum     需要合并的列号
     * @param rowNumFrom 合并起始行
     * @param rowNumTo   合并终止行
     */
    public static void mergeCellRangeInCol(Sheet sheet, CellStyle cellStyle, int colNum, int rowNumFrom, int rowNumTo) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNumFrom, rowNumTo, colNum, colNum);
        sheet.addMergedRegion(cellRangeAddress);

        for (int rowNum = rowNumFrom; rowNum <= rowNumTo; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = createCell(row, colNum);
            cell.setCellValue("");
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 添加下拉框
     *
     * @param sheet       Sheet
     * @param dataList    数据列表
     * @param startRowNum 起始行
     * @param endRowNum   终止行
     * @param colNum      列号
     */
    public static void addCellSelect(SXSSFSheet sheet, String[] dataList, int startRowNum, int endRowNum, int colNum) {
        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dataValidationHelper.createExplicitListConstraint(dataList);

        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList addressList = new CellRangeAddressList(startRowNum, endRowNum, colNum, colNum);
        XSSFDataValidation validation = (XSSFDataValidation) dataValidationHelper.createValidation(dvConstraint, addressList);

        // 数据有效性对象
        sheet.addValidationData(validation);
    }

    /**
     * 根据字典，将值转为对应的类型
     * @param cell
     * @param excelFieldRule
     * @return
     */
    public static Object getDictValue(Cell cell, ExcelFieldRule excelFieldRule) {
        Object cellValue = POIUtils.getCellValueAsClass(cell, String.class);

        // 从字典中获取对应的Value
        Object dictValue = ExcelDictCache.getValueCache(excelFieldRule.getDictType(), String.valueOf(cellValue));

        // 将Value转为对应的字段类型
        return POIUtils.getValueAsClass(dictValue, excelFieldRule.getFieldClass());
    }
}
