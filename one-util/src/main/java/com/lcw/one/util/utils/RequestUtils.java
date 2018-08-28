package com.lcw.one.util.utils;

import com.lcw.one.util.http.bean.BaseQueryCondition;
import com.lcw.one.util.utils.cipher.Encodes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @version 2017-05-04.
 * @auth Licw
 */
public class RequestUtils {

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"};

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取Http的基础路径
     *
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
        return basePath;
    }


    /**
     * 写数据到 response 中
     *
     * @param response
     * @param bytes       需要写入的字节数字
     * @param fileName    文件名称
     * @param contentType
     * @throws IOException
     */
    public static void writeBytesToResponse(HttpServletResponse response, byte[] bytes, String fileName, String contentType) throws IOException {

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
    }

    /**
     * 获取所有的header
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeaderMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = request.getHeaderNames();
        while (keys.hasMoreElements()) {
            String headName = keys.nextElement();
            map.put(headName, request.getHeader(headName));
        }
        map.put("RemoteHost", request.getRemoteHost() + ":" + request.getRemotePort());
        return map;
    }

    /**
     * 获取所有的Parameter
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, request.getParameter(key));
        }
        return map;
    }

    /**
     * 获取所有的Parameter，忽略参数中的密码
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameterMapSafe(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.contains("passowrd")) {
                continue;
            }
            map.put(key, request.getParameter(key));
        }
        return map;
    }

    /**
     * 获取所有的Attribute
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getAttributeMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration<String> keys = request.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, request.getAttribute(key));
        }
        return map;
    }

    /**
     * 格式化参数
     *
     * @param args
     * @return
     */
    public static String formatArgs(Object[] args) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(args)) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof HttpServletRequest) {
                    sb.append("request");
                } else if (arg instanceof HttpServletResponse) {
                    sb.append("response");
                } else if (arg instanceof BaseQueryCondition) {
                    sb.append(Reflections.getFieldMap(arg));
                } else {
                    sb.append(String.valueOf(arg));
                }
                if (i < args.length - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }
}
