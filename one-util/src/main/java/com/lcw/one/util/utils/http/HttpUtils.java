package com.lcw.one.util.utils.http;


import java.util.Map;

public class HttpUtils {
    private static Integer socketTimeout = Integer.valueOf(60000);
    private static Integer connectTimeout = Integer.valueOf(60000);
    private static Integer connectionRequestTimeout = Integer.valueOf(60000);

    public static ResponseContent getUrlRespContent(String url) {
        HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
        ResponseContent response = null;
        try {
            response = hw.doGet(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseContent();
            response.setContentBytes(e.getMessage().getBytes());
        }
        return response;
    }

    public static ResponseContent getUrlRespContent(String url, int timeOut) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent response = null;
        try {
            response = hw.doGet(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseContent();
            response.setContentBytes(e.getMessage().getBytes());
        }
        return response;
    }

    public static ResponseContent getUrlRespContent(String url, String urlEncoding) {
        HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
        ResponseContent response = null;
        try {
            response = hw.doGet(url, urlEncoding);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseContent();
            response.setContentBytes(e.getMessage().getBytes());
        }
        return response;
    }

    public static ResponseContent getUrlRespContent(String url, String urlEncoding, int timeOUt) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOUt), Integer.valueOf(timeOUt), Integer.valueOf(timeOUt));
        ResponseContent response = null;
        try {
            response = hw.doGet(url, urlEncoding);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseContent();
            response.setContentBytes(e.getMessage().getBytes());
        }
        return response;
    }

    public static ResponseContent postUrl(String url) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, "utf-8", null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, "utf-8", header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, int timeOut) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, "utf-8", null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, int timeOut, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, "utf-8", header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, String urlEncoding) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, urlEncoding, null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, String urlEncoding, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, urlEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, String urlEncoding, int timeOut) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, urlEncoding, null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postUrl(String url, String urlEncoding, int timeOut, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, urlEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, "utf-8", null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, "utf-8", header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, int timeOut) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, "utf-8", null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, int timeOut, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, "utf-8", header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, String urlEncoding) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, urlEncoding, null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, String urlEncoding, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, urlEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, String urlEncoding, int timeOut) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, urlEncoding, null);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postMapEntity(String url, Map<String, String> params, String urlEncoding, int timeOut, Map<String, String> header) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPost(url, params, urlEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static ResponseContent postJsonEntity(String url, String json, int timeOut) {
        HttpClientWrapper hw = new HttpClientWrapper(Integer.valueOf(timeOut), Integer.valueOf(timeOut), Integer.valueOf(timeOut));
        ResponseContent ret = null;
        try {
            ret = hw.doPostWithJson(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseContent();
            ret.setContentBytes(e.getMessage().getBytes());
        }
        return ret;
    }

    public static void testGet() {
        String url = "http://127.0.0.1:8888";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            String json = "{'msgtype':'01','name':'login" + i + "','desc':'用户登录','resptime':30}";
            ResponseContent responseContent = postJsonEntity(url, json, 60000);
            System.out.println(i + ":" + responseContent.getContent());
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
        try {
            for (; ; ) {
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
