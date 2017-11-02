package com.lcw.one.utils.cipher;


import com.lcw.one.util.utils.cipher.password.IPassWordUtil;
import com.lcw.one.util.utils.cipher.password.MD5PasswordUtil;
import com.lcw.one.util.utils.cipher.password.PasswordUtils;
import com.lcw.one.util.utils.cipher.password.SHA1PasswordUtil;
import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilsTest {

    @Test
    public void testPassword() {
        String plainPassword = "111111";
        String cryptPassword = PasswordUtils.encryptPassword(plainPassword);
        Assert.assertTrue(PasswordUtils.validatePassword(plainPassword, cryptPassword));
    }

    @Test
    public void testMd5Password() {
        IPassWordUtil passWordUtil = new MD5PasswordUtil();

        String plainPassword = "111111";
        String cryptPassword = passWordUtil.encryptPassword(plainPassword);
        Assert.assertTrue(passWordUtil.validatePassword(plainPassword, cryptPassword));
    }

    @Test
    public void testSha1Password() {
        IPassWordUtil passWordUtil = new SHA1PasswordUtil();

        String plainPassword = "111111";
        String cryptPassword = passWordUtil.encryptPassword(plainPassword);
        Assert.assertTrue(passWordUtil.validatePassword(plainPassword, cryptPassword));
    }
}
