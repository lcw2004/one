package com.lcw.one.util.excel;


import com.lcw.one.util.excel.rule.ExcelClassRule;
import com.lcw.one.util.excel.rule.ExcelFieldRule;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.ObjectUtils;
import com.lcw.one.util.utils.Reflections;
import com.lcw.one.util.utils.StringUtils;
import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelImporter {
    private static final Logger logger = LoggerFactory.getLogger(ExcelImporter.class);
    private Workbook workbook;
    private Class clazz;

    public ExcelImporter(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 初始化需要解析Excel数据
     *
     * @param is
     * @param fileType
     * @throws IOException
     */
    public void initExcel(InputStream is, String fileType) throws IOException {
        try {
            if (fileType.toLowerCase().equals("xls")) {
                this.workbook = new HSSFWorkbook(new BufferedInputStream(is));
            } else if (fileType.toLowerCase().equals("xlsx")) {
                this.workbook = new XSSFWorkbook(new BufferedInputStream(is));
            }
        } catch (NotOfficeXmlFileException | POIXMLException e) {
            logger.warn(e.getMessage(), e);
            throw new OneBaseException("不是一个合法的Excel文档");
        }
    }

    /**
     * 根据class上的注解解析Excel，默认忽略前两行，前两行作为标题
     *
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> List<T> parse() throws IOException {
        return parse(2, 0);
    }

    /**
     * 根据传入的规则列表解析Excel，默认忽略前两行，前两行作为标题
     *
     * @param excelClassRule 规则列表
     * @return
     * @throws IOException
     */
    public <T> List<T> parse(ExcelClassRule excelClassRule) throws IOException {
        return parse(excelClassRule, 2, 0);
    }

    /**
     * 根据class上的注解解析Excel，从指定的行开始解析
     *
     * @param headNum    指定行（从0开始）
     * @param sheetIndex 指定Sheet页（从0开始）
     * @return
     * @throws IOException
     */
    public <T> List<T> parse(int headNum, int sheetIndex) throws IOException {
        return parse(new ExcelClassRule(clazz), headNum, sheetIndex);
    }


    /**
     * 根据传入的规则列表解析Excel，从指定的行开始解析
     *
     * @param excelClassRule 规则列表
     * @param headNum        指定行（从0开始）
     * @param sheetIndex     指定Sheet页（从0开始）
     * @return
     */
    public <T> List<T> parse(ExcelClassRule excelClassRule, int headNum, int sheetIndex) {
        // 获取每个字段对应的类型
        excelClassRule.fetchFieldClass(clazz);
        logger.info(excelClassRule.toString());

        // 获取sheet
        Sheet sheet = this.workbook.getSheetAt(sheetIndex);
        if (sheet == null) {
            throw new OneBaseException("文档的第" + sheetIndex + "个Sheet不存在");
        }

        // 验证Excel
        validateExcel(sheet, headNum, excelClassRule);

        // 转换为数据列表
        List<T> tList = new LinkedList<>();
        for (int i = headNum; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) == null) {
                logger.warn("文档的第{}列是一个空行，可能是Excel出问题了，后面的数据都忽略导入！", i);
                break;
            }
            tList.add(this.parseRowAsObject(excelClassRule, i, sheet.getRow(i)));
        }

        return tList;
    }

    public void close() {
        POIUtils.closeWorkBooK(this.workbook);
    }

    /**
     * 将该列转换为一个对象
     *
     * @param excelClassRule 转换的规则
     * @param index          排序号
     * @param dataRow        Excel列
     * @param <T>
     * @return
     */
    private <T> T parseRowAsObject(ExcelClassRule excelClassRule, int index, Row dataRow) {
        // 注入每一行对应的值
        T t = Reflections.instance(clazz);
        for (ExcelFieldRule excelFieldRule : excelClassRule.getFieldRuleList()) {
            Cell cell = dataRow.getCell(excelFieldRule.getIndex());

            Object value;
            if (excelFieldRule.getFieldClass() == Map.class) {
                // Map类型获取字典值
                value = POIUtils.getCellValueAsClass(cell, String.class);
                if (StringUtils.isNotEmpty(excelFieldRule.getDictType())) {
                    // 获取字段值
                    value = POIUtils.getDictValue(cell, excelFieldRule.getDictType(), String.class);
                }
            } else {
                if (StringUtils.isNotEmpty(excelFieldRule.getDictType())) {
                    // 获取字段值
                    value = POIUtils.getDictValue(cell, excelFieldRule.getDictType(), String.class);
                } else {
                    // 获取原值
                    value = POIUtils.getCellValueAsClass(cell, excelFieldRule.getFieldClass());
                }
            }

            // 验证是否为null
            if (!excelFieldRule.isNullable()) {
                if (value == null || StringUtils.isEmpty(String.valueOf(value))) {
                    throw new OneBaseException("文档第" + (index + 1) + "行第" + excelFieldRule.getIndex() + "列的[" + excelFieldRule.getTitle() + "]字段不能为空");
                }
            }

            if (excelFieldRule.getFieldClass() == Map.class) {
                Object mapObj = Reflections.invokeGetter(t, excelFieldRule.getFieldName());
                if (mapObj != null) {
                    Map map = (Map) mapObj;
                    map.put(excelFieldRule.getTitle(), value);
                } else {
                    Map map = new HashMap();
                    map.put(excelFieldRule.getTitle(), value);
                    Reflections.invokeSetter2(t, excelFieldRule.getFieldName(), map);
                }
            } else {
                Reflections.invokeSetter2(t, excelFieldRule.getFieldName(), value);
            }
        }

        // 注入排序号
        if (StringUtils.isNotEmpty(excelClassRule.getOrderField())) {
            Reflections.invokeSetter2(t, excelClassRule.getOrderField(), index);
        }
        return t;
    }

    private void validateExcel(Sheet sheet, int headRowNumber, ExcelClassRule excelClassRule) {
        if (headRowNumber > 1) {
            Row headRow = sheet.getRow(headRowNumber - 1);
            if (headRow != null) {
                Map<Integer, ExcelFieldRule> map = ObjectUtils.asMapByFiled(excelClassRule.getFieldRuleList(), "index");
                for (int i = 0; i < headRow.getLastCellNum(); i++) {
                    String value = null;
                    Cell cell = headRow.getCell(i);
                    if (cell != null) {
                        value = (String) POIUtils.getCellValueAsClass(cell, String.class);
                    }

                    if (map != null && map.containsKey(i)) {
                        ExcelFieldRule rule = map.get(i);
                        if (!rule.getTitle().equals(value)) {
                            logger.warn("不是合法的模板文件，Excel头部[{}]，字段名称[{}]", value, rule.getTitle());
                            throw new OneBaseException("不是合法的模板文件，请不要修改标题");
                        }
                    }
                }
            }
        }
    }

}
