package com.lcw.one.util.utils.http;

/**
 * @version 2017-10-03.
 * @auth Licw
 */
public class HttpUtilsTest {

    public static void main(String[] args) {
        System.out.println(HttpUtils.getUrlRespContent("http://www.baidu.com").getContent());;
    }
}
