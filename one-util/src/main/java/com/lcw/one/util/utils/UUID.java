package com.lcw.one.util.utils;

public final class UUID {

    public static String randomUUID10() {
        return RandomUtils.randomString(10).toLowerCase();
    }

    public static String randomUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static String randomUUID(int length) {
        String uuId = randomUUID();
        if(length > uuId.length()) {
            length = uuId.length();
        }
        return uuId.substring(0, length);
    }

    public static String randomUserId() {
        return RandomUtils.randomNumberString(10).toLowerCase();
    }
}
