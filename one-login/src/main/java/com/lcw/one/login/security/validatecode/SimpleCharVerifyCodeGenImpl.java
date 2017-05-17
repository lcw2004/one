package com.lcw.one.login.security.validatecode;

import com.lcw.one.util.utils.RandomUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


public class SimpleCharVerifyCodeGenImpl implements IVerifyCodeGen {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCharVerifyCodeGenImpl.class);
    private static final String[] fontTypes = {"\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66"};
    private static final int VALICATE_CODE_LENGTH = 4;

    @Override
    public String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomUtils.randomString(VALICATE_CODE_LENGTH);
        createCharacter(graphics, randomStr);
        graphics.dispose();
        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }

    @Override
    public VerifyCode generate(int width, int height) throws IOException {
        ByteArrayOutputStream baos = null;
        VerifyCode verifyCode = null;
        try {
            baos = new ByteArrayOutputStream();
            String code = generate(width, height, baos);
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            verifyCode = null;
        } finally {
            IOUtils.closeQuietly(baos);
        }
        return verifyCode;
    }

    private static void fillBackground(Graphics graphics, int width, int height) {
        // 填充背景
//        graphics.setColor(randomColor(220, 250));
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            graphics.setColor(RandomUtils.randomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x, y, x1, y1);
        }
    }

    private void createCharacter(Graphics g, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            g.setColor(new Color(50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100)));
            g.setFont(new Font(fontTypes[RandomUtils.nextInt(fontTypes.length)], Font.BOLD, 26));
            g.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(8));
        }
    }
}
