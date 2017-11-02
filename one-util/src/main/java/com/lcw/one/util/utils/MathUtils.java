package com.lcw.one.util.utils;

import java.math.BigDecimal;

/**
 * @version 2017-07-18.
 * @auth Licw
 */
public class MathUtils {

    private static final int DEF_DIV_SCALE = 10;

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double addRoundHalfUP(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(scale, 4).doubleValue();
    }

    public static double addRoundUP(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(scale, 0).doubleValue();
    }

    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double subtractRoundHalfUP(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(scale, 4).doubleValue();
    }

    public static double subtractRoundUP(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(scale, 0).doubleValue();
    }

    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double multiplyRoundHalfUP(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(scale, 4).doubleValue();
    }

    public static double multiplyRoundUP(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(scale, 0).doubleValue();
    }

    public static double divide(double v1, double v2) {
        return divide(v1, v2, 10);
    }

    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v1));
        BigDecimal one = new BigDecimal(Double.toString(v2));
        return b.divide(one, scale, 4).doubleValue();
    }

    public static double divideRoundUP(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v1));
        BigDecimal one = new BigDecimal(Double.toString(v2));
        return b.divide(one, scale, 0).doubleValue();
    }

    public static double roundHalfUP(double v, int scale) {
        BigDecimal decimal = new BigDecimal(Double.toString(v));
        decimal = decimal.setScale(scale, 4);
        return decimal.doubleValue();
    }

    public static double roundUP(double v, int scale) {
        BigDecimal decimal = new BigDecimal(Double.toString(v));
        decimal = decimal.setScale(scale, 0);
        return decimal.doubleValue();
    }
}
