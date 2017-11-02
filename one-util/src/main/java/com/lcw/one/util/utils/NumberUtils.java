package com.lcw.one.util.utils;

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
}
