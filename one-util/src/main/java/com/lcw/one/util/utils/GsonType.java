package com.lcw.one.util.utils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonType {

    public static Type MAP_STRING_STRING_TYPE = new TypeToken<Map<String, String>>() {}.getType();
    public static Type MAP_STRING_OBJECT = new TypeToken<Map<String, Object>>() {}.getType();

}
