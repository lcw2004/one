package com.lcw.one.util.utils;

import com.lcw.one.util.utils.cipher.Encodes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 2017-05-04.
 * @auth Licw
 */
public class RequestUtils {

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
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
            if (key.indexOf("passowrd") >= 0) {
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

}
