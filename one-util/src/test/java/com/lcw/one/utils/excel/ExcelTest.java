package com.lcw.one.utils.excel;

import com.lcw.one.util.excel.ExcelUtils;
import com.lcw.one.util.excel.rule.ExcelClassRule;
import com.lcw.one.util.excel.rule.ExcelFieldRule;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.utils.excel.bean.User4Excel;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelTest {

    private List<User4Excel> testData() {
        List<User4Excel> userList = new ArrayList<>();
        userList.add(new User4Excel("张三", "男", 18, DateUtils.stringToDate("2000-01-01", DateUtils.yyyy_MM_dd_EN), "北京"));
        userList.add(new User4Excel("Lily", "女", 17, DateUtils.stringToDate("2001-02-02", DateUtils.yyyy_MM_dd_EN), "上海"));
        return userList;
    }

    private static ExcelClassRule buildExcelRule() {
        ExcelClassRule excelClassRule = new ExcelClassRule();
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("name", "姓名", false, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("gender", "性别", false, String.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("age", "年龄", false, Integer.class));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("birthDay", "生日", false, Date.class, null, "yyyy-MM-dd"));
        excelClassRule.addExcelFieldRule(new ExcelFieldRule("address", "地址", false, Integer.class));
        return excelClassRule;
    }

    @Test
    public void testExportUser() {
        List<User4Excel> userList = testData();
        byte[] bytes = ExcelUtils.saveAsExcel("测试用户", userList);
        FileUtil.writeFile(bytes, "D:\\test.xlsx");
    }

    @Test
    public void testImportUser() throws IOException {
        // 导出一份数据
        List<User4Excel> userList = testData();
        byte[] bytes = ExcelUtils.saveAsExcel("测试用户", userList);

        // 解析Excel文件
        List<User4Excel> userListFromExcel = ExcelUtils.parse(User4Excel.class, new ByteArrayInputStream(bytes));
        for (User4Excel user : userListFromExcel) {
            System.out.println(user);
        }
    }

    @Test
    public void testExportUserByRule() {
        List<User4Excel> userList = testData();
        byte[] bytes = ExcelUtils.saveAsExcel("测试用户", userList, buildExcelRule());
        FileUtil.writeFile(bytes, "D:\\test1.xlsx");
    }

    @Test
    public void testImportUserByRule() throws IOException {
        // 导出一份数据
        List<User4Excel> userList = testData();
        byte[] bytes = ExcelUtils.saveAsExcel("测试用户", userList, buildExcelRule());

        // 解析Excel文件
        List<User4Excel> userListFromExcel = ExcelUtils.parse(User4Excel.class, new ByteArrayInputStream(bytes));
        for (User4Excel user : userListFromExcel) {
            System.out.println(user);
        }
    }
}
