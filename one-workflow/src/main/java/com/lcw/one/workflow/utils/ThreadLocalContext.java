package com.lcw.one.workflow.utils;

import java.util.Map;

public class ThreadLocalContext {

    public static ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();

}
