package com.lcw.one.util.utils;

import org.apache.commons.lang3.Validate;

import java.awt.*;
import java.util.Random;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    private static final char[] CODE_ARRAY = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] NUMBER_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Random RANDOM = new Random();

    /**
     * 生成指定长度的字符串（数字+字母）
     *
     * @param length 需要生成的字符串的长度
     * @return
     */
    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.valueOf(CODE_ARRAY[RANDOM.nextInt(CODE_ARRAY.length)]));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的字符串（字母）
     *
     * @param length 需要生成的字符串的长度
     * @return
     */
    public static final String randomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.valueOf(NUMBER_ARRAY[RANDOM.nextInt(NUMBER_ARRAY.length)]));
        }
        return sb.toString();
    }

    /**
     * 随机生成颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    public static Color randomColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }

    /**
     * 随机生成一个数字
     *
     * @param bound
     * @return
     */
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * 随机生成一个字节数组
     *
     * @param length 需要生成的数组的长度
     * @return
     */
    public static byte[] randomBytes(int length) {
        Validate.isTrue(length > 0, "numBytes argument must be a positive integer (1 or larger)", length);

        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        return bytes;
    }
}
