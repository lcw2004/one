package com.lcw.one.util.utils;

import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * 计算工具类
 *
 * @version 2017-07-18.
 * @auth Licw
 */
public class MathUtils {

    private static final int DEFAULT_SCALE = 10;

    /**
     * 加法（value1 + value2）
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 加法（value1 + value2），结果四舍五入
     *
     * @param value1
     * @param value2
     * @param scale  小数点后位数
     * @return
     */
    public static double addRoundHalfUP(double value1, double value2, int scale) {
        return add(value1, value2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法（value1 + value2），结果向上取整
     *
     * @param value1
     * @param value2
     * @param scale  小数点后位数
     * @return
     */
    public static double addRoundUP(double value1, double value2, int scale) {
        return add(value1, value2, scale, BigDecimal.ROUND_UP);
    }

    /**
     * 减法（value1 - value2）
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double subtract(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 减法（value1 - value2），结果四舍五入
     *
     * @param value1
     * @param value2
     * @param scale  小数点后位数
     * @return
     */
    public static double subtractRoundHalfUP(double value1, double value2, int scale) {
        return subtract(value1, value2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法（value1 - value2），结果向上取整
     *
     * @param value1
     * @param value2
     * @param scale  小数点后位数
     * @return
     */
    public static double subtractRoundUP(double value1, double value2, int scale) {
        return subtract(value1, value2, scale, BigDecimal.ROUND_UP);
    }

    /**
     * 乘法（value1 * value2）
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double multiply(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 乘法（value1 * value2），结果四舍五入
     *
     * @param value1
     * @param value2
     * @param scale  小数点后位数
     * @return
     */
    public static double multiplyRoundHalfUP(double value1, double value2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 乘法（value1 * value2），结果向上取整
     *
     * @param value1
     * @param value2
     * @param scale 小数点后位数
     * @return
     */
    public static double multiplyRoundUP(double value1, double value2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_UP).doubleValue();
    }

    /**
     * 除法（value1 / value2）
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double divide(double value1, double value2) {
        return divide(value1, value2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法（value1 / value2），结果四舍五入
     *
     * @param value1
     * @param value2
     * @param scale 小数点后位数
     * @return
     */
    public static double divideRoundHalfUP(double value1, double value2, int scale) {
        return divide(value1, value2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法（value1 / value2），结果向上取整
     *
     * @param value1
     * @param value2
     * @param scale  小数点后位数
     * @return
     */
    public static double divideRoundUP(double value1, double value2, int scale) {
        return divide(value1, value2, scale, BigDecimal.ROUND_UP);
    }

    /**
     * 四舍五入
     *
     * @param value
     * @param scale 小数点后位数
     * @return
     */
    public static double roundHalfUP(double value, int scale) {
        BigDecimal decimal = new BigDecimal(Double.toString(value));
        decimal = decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return decimal.doubleValue();
    }

    /**
     * 向上取整
     *
     * @param value
     * @param scale 小数点后位数
     * @return
     */
    public static double roundUP(double value, int scale) {
        BigDecimal decimal = new BigDecimal(Double.toString(value));
        decimal = decimal.setScale(scale, BigDecimal.ROUND_UP);
        return decimal.doubleValue();
    }


    /**
     * 加法
     *
     * @param value1
     * @param value2
     * @param scale        小数点后位数
     * @param roundingMode 舍入方式
     * @return
     */
    private static double add(double value1, double value2, int scale, int roundingMode) {
        Assert.isTrue(scale >= 0, "The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        BigDecimal result = b1.add(b2);
        result = result.setScale(scale, roundingMode);
        return result.doubleValue();
    }

    /**
     * 减法
     *
     * @param value1
     * @param value2
     * @param scale        小数点后位数
     * @param roundingMode 舍入方式
     * @return
     */
    private static double subtract(double value1, double value2, int scale, int roundingMode) {
        Assert.isTrue(scale >= 0, "The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        BigDecimal result = b1.subtract(b2);
        result = result.setScale(scale, roundingMode);
        return result.doubleValue();
    }

    /**
     * 乘法
     *
     * @param value1
     * @param value2
     * @param scale        小数点后位数
     * @param roundingMode 舍入方式
     * @return
     */
    private static double multiply(double value1, double value2, int scale, int roundingMode) {
        Assert.isTrue(scale >= 0, "The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        BigDecimal result = b1.multiply(b2);
        result = result.setScale(scale, roundingMode);
        return result.doubleValue();
    }

    /**
     * 除法
     *
     * @param value1
     * @param value2
     * @param scale        小数点后位数
     * @param roundingMode 舍入方式
     * @return
     */
    private static double divide(double value1, double value2, int scale, int roundingMode) {
        Assert.isTrue(scale >= 0, "The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        BigDecimal result = b1.divide(b2, scale, roundingMode);
        return result.doubleValue();
    }


}
