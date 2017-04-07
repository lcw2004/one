package com.lcw.one;

import com.lcw.one.common.mapper.JsonMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class RestTemplateWithCookies extends RestTemplate {

    private List<String> cookies = null;

    public RestTemplateWithCookies() {
    }

    public RestTemplateWithCookies(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    private synchronized List<String> getCoookies() {
        return cookies;
    }

    private synchronized void setCoookies(List<String> cookies) {
        this.cookies = cookies;
    }

    public synchronized void resetCoookies() {
        this.cookies = null;
    }

    private void processHeaders(HttpHeaders headers) {
        final List<String> cookies = headers.get("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {
            setCoookies(cookies);
        }
    }

    @Override
    protected <T extends Object> T doExecute(URI url, HttpMethod method, final RequestCallback requestCallback, final ResponseExtractor<T> responseExtractor) throws RestClientException {
        logger.info("======================= Start =======================");
        final List<String> cookies = getCoookies();

        T t = super.doExecute(url, method, new RequestCallback() {
            @Override
            public void doWithRequest(ClientHttpRequest chr) throws IOException {
                // 提取Cookie
                if (cookies != null) {
                    for (String cookie : cookies) {
                        chr.getHeaders().add("Cookie", cookie);
                    }
                }
                requestCallback.doWithRequest(chr);
            }

        }, new ResponseExtractor<T>() {
            @Override
            public T extractData(ClientHttpResponse chr) throws IOException {
                processHeaders(chr.getHeaders());
                T t = responseExtractor.extractData(chr);
                logger.info(JsonMapper.getInstance().toJson(t));
                return t;
            }
        });

        logger.info("======================= End =======================");
        return t;
    }

}