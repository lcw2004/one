package com.lcw.one.util.utils;

import com.lcw.one.util.utils.cipher.Encodes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public static void writeExcelBytesToResponse(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
    }

    public static String getBasePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
        return basePath;
    }
}
