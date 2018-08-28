package com.lcw.one.codegen;

import com.lcw.one.codegen.util.CodeGenUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PomUtilsTest {


    @Before
    public void before() {

    }


    @Test
    public void testGetPackageFromModule() {
        Assert.assertEquals(CodeGenUtil.getPackageFromModule("one-sys"), "com.lcw.one.sys");
        Assert.assertEquals(CodeGenUtil.getPackageFromModule("one-code-gen"), "com.lcw.one.codegen");
        Assert.assertEquals(CodeGenUtil.getPackageFromModule("one-test-camel"), "com.lcw.one.testCamel");
    }


}
