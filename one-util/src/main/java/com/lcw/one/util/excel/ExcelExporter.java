package com.lcw.one.util.excel;

import com.lcw.one.util.excel.rule.ExcelClassRule;
import com.lcw.one.util.excel.rule.ExcelFieldRule;
import com.lcw.one.util.excel.style.IExcelStyle;
import com.lcw.one.util.excel.style.TemplateExcelStyle;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.ObjectUtils;
import com.lcw.one.util.utils.Reflections;
import com.lcw.one.util.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * Excel导出工具
 */
public class ExcelExporter {

    protected SXSSFWorkbook workbook;
    protected SXSSFSheet sheet;

    // 当前处理的列
    protected int currentRowNum = 0;

    /**
     * Excel样式生成器
     */
    protected IExcelStyle excelStyle;

    /**
     * Excel-Class对应规则
     */
    protected ExcelClassRule excelClassRule;

    /**
     * Excel文档的标题
     */
    private String title;

    /**
     * 默认行高
     */
    public static final int DEFAULT_HEIGHT_IN_POINTS = 20;

    /**
     * 按类上的注解导出数据到Excel文档中
     *
     * @param dataList 数据
     */
    public void export(List dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        export(dataList, new ExcelClassRule(dataList.get(0).getClass()));
    }

    /**
     * 按规则将数据导出到Excel文档中
     *
     * @param dataList       数据
     * @param excelClassRule 规则列表
     */
    public void export(List dataList, ExcelClassRule excelClassRule) {
        this.excelClassRule = excelClassRule;

        // 初始化文档
        initWorkbook();

        // 初始化标题
        initTitle(excelClassRule.getFieldRuleList().size() - 1);

        // 初始化标题栏
        initHead();

        // 添加下拉选择数据
        addDataSelect();

        // 导入数据
        addData(dataList);

        autoSize();
    }

    public void close() {
        POIUtils.closeWorkBooK(this.workbook);
    }

    public byte[] toBytes() {
        return POIUtils.toBytes(this.workbook);
    }

    protected void initWorkbook() {
        this.workbook = new SXSSFWorkbook(500);
        this.excelStyle = new TemplateExcelStyle(workbook);

        this.sheet = workbook.createSheet("Export");
        this.sheet.trackAllColumnsForAutoSizing();
    }

    protected void autoSize() {
        POIUtils.autoSize(sheet);
    }

    /**
     * 初始化标题
     * @param maxColNumber 最大列数
     */
    protected void initTitle(int maxColNumber) {
        int rowNum = this.currentRowNum++;
        Row titleRow = POIUtils.createRow(sheet, rowNum);
        titleRow.setHeightInPoints(30);

        // 合并单元格
        POIUtils.mergeCellRangeInRow(sheet, excelStyle.getTitleStyle(), rowNum, 0, maxColNumber);

        Cell cell = titleRow.getCell(0);
        cell.setCellValue(title);
        cell.setCellStyle(excelStyle.getTitleStyle());
    }

    /**
     * 初始化Excel标题
     *
     */
    protected void initHead() {
        Row headerRow = sheet.createRow(this.currentRowNum++);
        headerRow.setHeightInPoints(25);
        for (ExcelFieldRule excelFieldRule : excelClassRule.getFieldRuleList()) {
            Cell cell = headerRow.createCell(excelFieldRule.getIndex());
            cell.setCellValue(excelFieldRule.getTitle());
            cell.setCellStyle(excelStyle.getHeaderStyle());
        }
    }

    protected void addDataSelect() {
        for (ExcelFieldRule excelFieldRule : excelClassRule.getFieldRuleList()) {
            if (StringUtils.isNotEmpty(excelFieldRule.getDictType())) {
                String[] labelArray = ExcelDictCache.labelList(excelFieldRule.getDictType());
                if (labelArray == null) {
                    throw new OneBaseException("属性[" + excelFieldRule.getFieldName() + "],字典类型[" + excelFieldRule.getDictType() + "]对应的数据不存在");
                }
                POIUtils.addCellSelect(sheet, labelArray, 2, 1000, excelFieldRule.getIndex());
            }
        }
    }


    /**
     * 添加数据到Excel文档
     *
     * @param dataList           数据
     */
    protected <T> void addData(List<T> dataList) {
        addData(dataList, currentRowNum);
    }

    /**
     * 添加数据到Excel文档
     *
     * @param dataList           数据
     * @param startRowNum        数据起始行数
     */
    protected <T> void addData(List<T> dataList, int startRowNum) {
        // 对数据按指定的排序号排序
        ObjectUtils.orderList(dataList, this.excelClassRule.getOrderField());

        this.currentRowNum = startRowNum;
        for (T data : dataList) {
            Row dataRow = sheet.createRow(this.currentRowNum++);
            dataRow.setHeightInPoints(DEFAULT_HEIGHT_IN_POINTS);
            for (ExcelFieldRule excelFieldRule : excelClassRule.getFieldRuleList()) {
                Object value = Reflections.invokeGetter(data, excelFieldRule.getFieldName());
                if (excelFieldRule.getFieldClass() != Map.class && StringUtils.isNotEmpty(excelFieldRule.getDictType())) {
                    String dictLabel = ExcelDictCache.getLabelCache(excelFieldRule.getDictType(), String.valueOf(value));
                    POIUtils.setCellValue(workbook, dataRow, excelFieldRule.getIndex(), dictLabel, excelStyle.getNormalStyle(), excelFieldRule);
                } else {
                    POIUtils.setCellValue(workbook, dataRow, excelFieldRule.getIndex(), value, excelStyle.getNormalStyle(), excelFieldRule);
                }

            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
