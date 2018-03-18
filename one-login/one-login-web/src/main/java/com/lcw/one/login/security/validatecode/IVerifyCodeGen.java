package com.lcw.one.login.security.validatecode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成接口
 */
public interface IVerifyCodeGen {

    /**
     * 生成验证码并返回code，将图片写到传入的os中
     * @param width 宽度
     * @param height 高度
     * @param os
     * @return
     * @throws IOException
     */
    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     * @param width 宽度
     * @param height 高度
     * @return
     * @throws IOException
     */
    VerifyCode generate(int width, int height) throws IOException;
}
