package com.lcw.one.util.utils.http;

import com.lcw.one.util.utils.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientWrapper {
    private static final Logger log = LoggerFactory.getLogger(HttpClientWrapper.class);
    private Integer socketTimeout = Integer.valueOf(60000);
    private Integer connectTimeout = Integer.valueOf(60000);
    private Integer connectionRequestTimeout = Integer.valueOf(60000);
    private static CloseableHttpClient client;
    private RequestConfig requestConfig;
    private static PoolingHttpClientConnectionManager connManager = null;
    public static final int MAX_ROUTE_CONNECTIONS = 200;
    public static final int MAX_TOTAL_CONNECTIONS = 800;


    public HttpClientWrapper() {
        System.setProperty("jsse.enableSNIExtension", "false");

        DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(0, false);
        client = HttpClients.custom().setConnectionManager(connManager).setRetryHandler(retryHandler).build();
        this.requestConfig = RequestConfig.custom().setConnectionRequestTimeout(this.connectionRequestTimeout.intValue()).setConnectTimeout(this.connectTimeout.intValue()).setSocketTimeout(this.socketTimeout.intValue()).build();
    }

    public HttpClientWrapper(Integer connectionRequestTimeout, Integer connectTimeout, Integer socketTimeout) {
        System.setProperty("jsse.enableSNIExtension", "false");
        this.socketTimeout = socketTimeout;
        this.connectTimeout = connectTimeout;
        this.connectionRequestTimeout = connectionRequestTimeout;

        DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(0, false);
        client = HttpClients.custom().setConnectionManager(connManager).setRetryHandler(retryHandler).build();
        this.requestConfig = RequestConfig.custom().setConnectionRequestTimeout(this.connectionRequestTimeout.intValue()).setConnectTimeout(this.connectTimeout.intValue()).setSocketTimeout(this.socketTimeout.intValue()).build();
    }

    public ResponseContent doGet(String url, String charset) {
        try {
            HttpEntity entity = null;
            HttpRequestBase request = null;
            CloseableHttpResponse response = null;
            try {
                request = new HttpGet(url);
                request.setConfig(this.requestConfig);
                request.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");

                request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                request.setHeader("Accept-Encoding", "gzip,deflate,sdch");
                request.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
                request.setHeader("Cache-Control", "max-age=0");


                request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");


                response = client.execute(request);

                entity = response.getEntity();
                StatusLine statusLine = response.getStatusLine();
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statusLine.getStatusCode());
                getResponseContent(entity, ret);
                return ret;
            } finally {
                close(entity, request, response);
            }
        } catch (Exception e) {
            log.error(String.format("执行请求%s异常", new Object[]{url}), e);
        }
        return null;

    }

    public ResponseContent doGet(String url, Map<String, String> params, String charset) {
        try {
            if ((params != null) && (!params.isEmpty())) {
                List<NameValuePair> pairs = new ArrayList(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = (String) entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair((String) entry.getKey(), value));
                    }
                }
                url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpEntity entity = null;
            HttpRequestBase request = null;
            CloseableHttpResponse response = null;
            try {
                request = new HttpGet(url);
                request.setConfig(this.requestConfig);
                request.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
                response = client.execute(request);

                entity = response.getEntity();
                StatusLine statusLine = response.getStatusLine();
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statusLine.getStatusCode());
                getResponseContent(entity, ret);
                return ret;
            } finally {
                close(entity, request, response);
            }
        } catch (Exception e) {
            log.error(String.format("执行请求%s,参数%s异常", new Object[]{url, map2Str(params)}), e);
        }
        return null;
    }

    public ResponseContent doPost(String url, Map<String, String> params, String charset, Map<String, String> header) {
        try {
            List<NameValuePair> pairs = null;
            if ((params != null) && (!params.isEmpty())) {
                pairs = new ArrayList(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = (String) entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair((String) entry.getKey(), value));
                    }
                }
            }
            HttpEntity entity = null;
            Object request = null;
            CloseableHttpResponse response = null;
            try {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
                request = httpPost;
                ((HttpRequestBase) request).setConfig(this.requestConfig);
                ((HttpRequestBase) request).addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
                if ((header != null) && (!header.isEmpty())) {
                    for (Map.Entry<String, String> entry : header.entrySet()) {
                        ((HttpRequestBase) request).addHeader((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                response = client.execute((HttpUriRequest) request);
                entity = response.getEntity();
                StatusLine statusLine = response.getStatusLine();
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statusLine.getStatusCode());
                getResponseContent(entity, ret);
                return ret;
            } finally {
                close(entity, (HttpRequestBase) request, response);
            }

        } catch (Exception e) {
            log.error(String.format("执行请求%s,参数%s异常", new Object[]{url, map2Str(params)}), e);
        }
        return null;
    }

    public ResponseContent doPost(String url, String charset, Map<String, String> header) {
        try {
            HttpEntity entity = null;
            HttpRequestBase request = null;
            CloseableHttpResponse response = null;
            try {
                HttpPost httpPost = new HttpPost(url);
                request = httpPost;
                request.setConfig(this.requestConfig);
                request.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
                if ((header != null) && (!header.isEmpty())) {
                    for (Map.Entry<String, String> entry : header.entrySet()) {
                        request.addHeader((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                response = client.execute(request);
                entity = response.getEntity();
                StatusLine statusLine = response.getStatusLine();
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statusLine.getStatusCode());
                getResponseContent(entity, ret);
                return ret;
            } finally {
                close(entity, request, response);
            }
        } catch (Exception e) {
            log.error(String.format("执行请求%s异常", new Object[]{url}), e);
        }
        return null;
    }

    public ResponseContent doPostWithJson(String url, String json) {
        try {
            HttpEntity entity = null;
            HttpRequestBase request = null;
            CloseableHttpResponse response = null;
            try {
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("Content-Type", "application/json;utf-8");

                StringEntity se = new StringEntity(json, "UTF-8");

                se.setContentType("application/json;utf-8");
                se.setContentEncoding(new BasicHeader("Content-Type", "application/json;utf-8"));
                httpPost.setEntity(se);

                request = httpPost;
                request.setConfig(this.requestConfig);
                request.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
                response = client.execute(request);
                entity = response.getEntity();
                StatusLine statusLine = response.getStatusLine();
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statusLine.getStatusCode());
                getResponseContent(entity, ret);
                return ret;
            } finally {
                close(entity, request, response);
            }
        } catch (Exception e) {
            log.error(String.format("执行请求%s,参数%s异常", new Object[]{url, json}), e);
        }
        return null;

    }

    private void getResponseContent(HttpEntity entity, ResponseContent ret)
            throws IOException {
        Header enHeader = entity.getContentEncoding();
        if (enHeader != null) {
            String charset = enHeader.getValue().toLowerCase();
            ret.setEncoding(charset);
        }
        String contenttype = getResponseContentType(entity);
        ret.setContentType(contenttype);
        ret.setContentTypeString(getResponseContentTypeString(entity));
        ret.setContentBytes(EntityUtils.toByteArray(entity));
    }

    private void close(HttpEntity entity, HttpRequestBase request, CloseableHttpResponse response)
            throws IOException {
        if (request != null) {
            request.releaseConnection();
        }
        if (entity != null) {
            entity.getContent().close();
        }
        if (response != null) {
            response.close();
        }
    }

    private String getResponseContentType(HttpEntity method) {
        Header contenttype = method.getContentType();
        if (contenttype == null) {
            return null;
        }
        String ret = null;
        try {
            HeaderElement[] hes = contenttype.getElements();
            if ((hes != null) && (hes.length > 0)) {
                ret = hes[0].getName();
            }
        } catch (Exception localException) {
        }
        return ret;
    }

    private String getResponseContentTypeString(HttpEntity method) {
        Header contenttype = method.getContentType();
        if (contenttype == null) {
            return null;
        }
        return contenttype.getValue();
    }


    public static String map2Str(Map<String, String> params) {
        StringBuffer url = new StringBuffer();
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = (String) entry.getKey();
            String paramValue = (String) entry.getValue();
            if (i == 0) {
                url.append("?");
            } else {
                url.append("&");
            }
            url.append(paramName).append("=").append(paramValue);
            i++;
        }
        return url.toString();
    }

    public static String map2Str(Map<String, String> params, String encoding) {
        StringBuffer url = new StringBuffer();
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = (String) entry.getKey();
            String paramValue = (String) entry.getValue();
            if (i > 0) {
                url.append("&");
            }
            if (!StringUtils.isEmpty(paramValue)) {
                try {
                    paramValue = URLEncoder.encode(paramValue, encoding);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            url.append(paramName).append("=").append(paramValue);
            i++;
        }
        return url.toString();
    }
}
