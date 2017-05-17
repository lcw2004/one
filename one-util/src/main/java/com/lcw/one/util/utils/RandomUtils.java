package com.lcw.one.util.utils;


import java.awt.*;
import java.util.Random;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    private static final char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] numberArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static Random random = new Random();

    public static final String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.valueOf(codeSeq[random.nextInt(codeSeq.length)]));
        }
        return sb.toString();
    }

    public static final String randomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.valueOf(numberArray[random.nextInt(numberArray.length)]));
        }
        return sb.toString();
    }

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

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
