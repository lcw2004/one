package com.lcw.one.util.excel;

import com.lcw.one.util.excel.rule.ExcelClassRule;
import com.lcw.one.util.excel.style.TemplateExcelStyle;
import com.lcw.one.util.exception.OneBaseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public class ExcelTemplateExporter extends ExcelExporter {

    private Logger logger = LoggerFactory.getLogger(ExcelTemplateExporter.class);

    private String templatePath;
    private XSSFWorkbook xssfWorkbook;

    public ExcelTemplateExporter(String templatePath, ExcelClassRule excelClassRule) {
        this.templatePath = templatePath;
        this.excelClassRule = excelClassRule;
    }

    @Override
    public void initWorkbook() {
        Resource resource = new ClassPathResource(templatePath);
        if (!resource.exists()) {
            logger.error("加载Excel模板失败，资源不存在");
            throw new OneBaseException("加载Excel模板失败，资源不存在");
        }

        try {
            this.xssfWorkbook = new XSSFWorkbook(resource.getInputStream());
            this.workbook = new SXSSFWorkbook(this.xssfWorkbook);
            this.excelStyle = new TemplateExcelStyle(workbook);
            this.sheet = this.workbook.getSheetAt(0);
            this.sheet.trackAllColumnsForAutoSizing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initHeader(String title) {
        initHeader(title, 0, 0);
    }

    public void initHeader(String title, int rowNum, int colNum) {
        Row row = POIUtils.createRow(this.xssfWorkbook.getSheetAt(0), rowNum);
        if (row != null) {
            Cell cell = row.getCell(colNum);
            if (cell != null) {
                cell.setCellValue(title);
            }
        }
    }

    public void export(List dataList, int startRowNum) {
        addData(dataList, startRowNum);
    }

}
