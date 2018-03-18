package com.lcw.one.util.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @version 2017-05-19.
 * @auth Licw
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    public static int safeLongToInt(long input) {
        if (input < Integer.MIN_VALUE || input > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(input + " cannot be cast to int without changing its value.");
        }
        return (int) input;
    }

    public static Double valueOf(Double value, Double defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public static String formatDouble(Double value) {
        if (value == null) {
            return "";
        }
        if (value == 0d) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("########.00");
        return df.format(value.doubleValue());
    }

    public static Boolean isNumber(Object inputValue) {
        Class clazz = inputValue.getClass();
        return clazz == Integer.class || clazz == Double.class || clazz == Float.class;
    }

    public static String numberToString(Object inputValue) {
        if (inputValue.getClass() == Integer.class) {
            return new BigDecimal((Integer) inputValue).toPlainString();
        } else if (inputValue.getClass() == Double.class) {
            return new BigDecimal((Double) inputValue).toPlainString();
        } else if (inputValue.getClass() == Float.class) {
            return new BigDecimal((Float) inputValue).toPlainString();
        } else {
            return "";
        }
    }
}
