package com.lcw.one.utils.excel;

import com.lcw.one.util.excel.ExcelExporter;
import com.lcw.one.util.excel.ExcelUtils;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.utils.excel.bean.User4Excel;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    private List<User4Excel> testData() {
        List<User4Excel> userList = new ArrayList<>();
        userList.add(new User4Excel("张三", "男", 18));
        userList.add(new User4Excel("Lily", "女", 17));
        return userList;
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
}
