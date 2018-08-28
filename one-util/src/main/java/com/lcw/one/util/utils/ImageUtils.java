package com.lcw.one.util.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @version 2017-08-15.
 * @auth Licw
 */
public class ImageUtils {

    /**
     * 剪切图片，去掉图片的空白部分
     *
     * @param is         图片输入流
     * @param spaceWidth 图片留白长度
     * @return
     * @throws IOException
     */
    public static BufferedImage cutImageBlank(InputStream is, int spaceWidth) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(is);

        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();

        // 获取图片的左上角以及右下角的坐标
        int leftUpX = 0;
        int leftUpY = 0;
        int rightDownX = 0;
        int rightDownY = 0;
        for (int i = bufferedImage.getMinX(); i < bufferedImage.getWidth(); i++) {
            for (int j = bufferedImage.getMinY(); j < bufferedImage.getHeight(); j++) {
                int rgb = bufferedImage.getRGB(i, j);
                if (rgb != 0) {
                    if (leftUpX == 0 || leftUpX > i)
                        leftUpX = i;
                    if (leftUpY == 0 || leftUpY > j)
                        leftUpY = j;
                    if (rightDownX < i)
                        rightDownX = i;
                    if (rightDownY < j)
                        rightDownY = j;
                }
            }
        }

        // 剪切图片
        int newLeftUpX = toIntWithMin(leftUpX - spaceWidth, 0);
        int newLeftUpY = toIntWithMin(leftUpY - spaceWidth, 0);
        int newRightDownX = toIntWithMax(rightDownX + spaceWidth, imageWidth);
        int newRightDownY = toIntWithMax(rightDownY + spaceWidth, imageHeight);
        return bufferedImage.getSubimage(newLeftUpX, newLeftUpY, (newRightDownX - newLeftUpX), (newRightDownY - newLeftUpY));
    }


    /**
     * 剪切图片，去掉图片的空白部分
     *
     * @param is         图片输入流
     * @param spaceWidth 图片留白长度
     * @return
     */
    public static byte[] cutImage(InputStream is, int spaceWidth) throws IOException {
        return toBytes(cutImageBlank(is, spaceWidth), "PNG");
    }

    /**
     * 剪切图片，去掉图片的空白部分
     *
     * @param is         图片输入流
     * @param spaceWidth 图片留白长度
     * @return
     */
    public static void cutImage(InputStream is, OutputStream os, int spaceWidth) throws IOException {
        ImageIO.write(cutImageBlank(is, spaceWidth), "PNG", new BufferedOutputStream(os));
    }


    public static int toIntWithMin(int input, int minValue) {
        return input > minValue ? input : minValue;
    }

    public static int toIntWithMax(int input, int maxValue) {
        return input > maxValue ? maxValue : input;
    }

    private static byte[] toBytes(BufferedImage image, String formatName) {
        byte[] bytes = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, formatName, os);
            bytes = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
        return bytes;
    }
}
