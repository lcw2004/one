package com.lcw.one.workflow.config;

import java.util.Map;

public class ThreadLocalContext {

    public static ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();

}
