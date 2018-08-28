package com.lcw.one.util.excel;

import com.lcw.one.util.excel.rule.ExcelClassRule;
import com.lcw.one.util.utils.RequestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExcelUtils {

    public static final String EXCEL_XLSX = "xlsx";

    /**
     * 将Excel文件解析为一个List
     *
     * @param <T>
     * @param tClass 需要解析出的对象class
     * @param is     Excel文件输入流
     * @return
     * @throws IOException
     */
    public static <T> List<T> parse(Class<T> tClass, InputStream is) throws IOException {
        ExcelImporter excelImporter = null;
        List<T> tList;
        try {
            excelImporter = new ExcelImporter(tClass);
            excelImporter.initExcel(is, EXCEL_XLSX);
            tList = excelImporter.parse();
        } finally {
            if (excelImporter != null) {
                excelImporter.close();
            }
        }
        return tList;
    }

    /**
     * 将Excel文件解析为一个List
     *
     * @param <T>
     * @param tClass         需要解析出的对象class
     * @param is             Excel文件输入流
     * @param excelClassRule 文档解析规则
     * @return
     * @throws IOException
     */
    public static <T> List<T> parse(Class<T> tClass, InputStream is, ExcelClassRule excelClassRule) throws IOException {
        ExcelImporter excelImporter = null;
        List<T> tList;
        try {
            excelImporter = new ExcelImporter(tClass);
            excelImporter.initExcel(is, EXCEL_XLSX);
            tList = excelImporter.parse(excelClassRule);
        } finally {
            if (excelImporter != null) {
                excelImporter.close();
            }
        }
        return tList;
    }


    /**
     * 将数据存为Excel文件
     *
     * @param title 标题
     * @param tList 数据
     * @return
     */
    public static byte[] saveAsExcel(String title, List tList) {
        ExcelExporter excelExporter = null;
        try {
            excelExporter = new ExcelExporter();
            excelExporter.setTitle(title);
            excelExporter.export(tList);
            return excelExporter.toBytes();
        } finally {
            if (excelExporter != null) {
                excelExporter.close();
            }
        }
    }

    /**
     * 将数据按指定的规则存为Excel文件
     *
     * @param title          标题
     * @param tList          数据
     * @param excelClassRule 规则列表
     * @return
     */
    public static byte[] saveAsExcel(String title, List tList, ExcelClassRule excelClassRule) {
        ExcelExporter excelExporter = null;
        try {
            excelExporter = new ExcelExporter();
            excelExporter.setTitle(title);
            excelExporter.export(tList, excelClassRule);
            return excelExporter.toBytes();
        } finally {
            if (excelExporter != null) {
                excelExporter.close();
            }
        }
    }

    /**
     * 返回Excel文件到前端中
     *
     * @param response
     * @param bytes    excel数据
     * @param fileName 文件名称（不用传入文件类型）
     * @throws IOException
     */
    public static void writeExcelBytesToResponse(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
        String contentType = "application/octet-stream; charset=utf-8";
        RequestUtils.writeBytesToResponse(response, bytes, fileName + ".xlsx", contentType);
    }

}
