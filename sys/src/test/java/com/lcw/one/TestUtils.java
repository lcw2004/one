package com.lcw.one;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

public class TestUtils {

    private static int port = 8080;
    private static HttpHeaders httpHeaders;
    public static RestTemplateWithCookies REST_TEMPLATE = new RestTemplateWithCookies();

    public static String getBaseUrl() {
        final String restApiUrl = "one/a/rest/";
        final String baseUrl = "http://localhost:" + port + "/" + restApiUrl;
        return baseUrl;
    }

    public static HttpEntity jsonEntity() {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", "application/json");
        }
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return httpEntity;
    }

}
