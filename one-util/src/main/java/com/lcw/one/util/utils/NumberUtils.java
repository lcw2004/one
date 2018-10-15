package com.lcw.one.util.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @version 2017-05-19.
 * @auth Licw
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    public static Double safeDouble(Double input) {
        return input == null ? 0D : input;
    }

    public static Integer safeInteger(Integer input) {
        return input == null ? 0 : input;
    }

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

    public static int compareTo(Double value1, Double value2) {
        return new BigDecimal(value1).compareTo(new BigDecimal(value2));
    }

    public static String formatDouble(Double value) {
        return formatDouble(value, "########.00");
    }

    public static String formatDoubleNoDot(Double value) {
        return formatDouble(value, "########");
    }

    public static String formatDouble(Double value, String pattern) {
        if (value == null) {
            return "";
        }
        if (value == 0d) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat(pattern);
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
            String value = NumberUtils.formatDoubleNoDot((Double) inputValue);
            if (value.endsWith(".00")) {
                value = value.substring(0, value.length() - 3);
            }
            return value;
        } else if (inputValue.getClass() == Float.class) {
            return new BigDecimal((Float) inputValue).toPlainString();
        } else {
            return "";
        }
    }

    public static int boolToInt(boolean value) {
        return value ? 1 : 0;
    }

    public static boolean isEquals(Double value1, Double value2) {
        return compareTo(value1, value2) == 0;
    }
}
