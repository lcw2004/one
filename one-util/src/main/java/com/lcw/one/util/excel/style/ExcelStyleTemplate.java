package com.lcw.one.util.excel.style;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.CollectionUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

/**
 * Excel样式模板，整个系统只加载一次，重复使用
 *
 * @author lcw
 */
public class ExcelStyleTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelStyleTemplate.class);
    private static Map<ExcelCellStyleEnum, CellStyle> cellStyleMap;

    public static void loadExcelTemplate() {
        try {
            Resource resource = new ClassPathResource("ExcelTemplate.xlsx");
            if (!resource.exists()) {
                LOGGER.error("加载Excel模板失败，资源不存在");
                throw new OneBaseException("加载Excel模板失败，资源不存在");
            }

            ExcelStyleTemplateParser excelStyleTemplateParser = new ExcelStyleTemplateParser();
            cellStyleMap = excelStyleTemplateParser.loadCellStyle(resource.getInputStream());

            if (CollectionUtils.isEmpty(cellStyleMap)) {
                LOGGER.error("加载Excel模板失败");
                throw new OneBaseException("加载Excel模板失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取ExcelCellStyleEnum对应的CellStyle
     *
     * @param cellStyleEnum
     * @return
     */
    public static CellStyle getCellStyle(ExcelCellStyleEnum cellStyleEnum) {
        if (cellStyleMap == null) {
            loadExcelTemplate();
        }
        return cellStyleMap.get(cellStyleEnum);
    }
}
