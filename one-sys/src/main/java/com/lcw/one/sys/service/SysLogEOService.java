package com.lcw.one.sys.service;

import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.sys.dao.SysLogEODao;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.http.CookieUtils;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.util.utils.RequestUtils;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.UUID;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service
public class SysLogEOService extends CrudService<SysLogEODao, SysLogEO, String> {

    public SysLogEO save(HttpServletRequest request, long executeTime, String remark) {
        Map<String, String> headers = RequestUtils.getHeaderMap(request);
        String userAgent = headers.get("user-agent");
        SysLogEO sysLogEO = new SysLogEO();
        sysLogEO.setLogId(UUID.randomUUID());
        sysLogEO.setCreateTime(new Date());
        sysLogEO.setHttpMethod(request.getMethod());
        sysLogEO.setHttpUri(StringUtils.limitLength(request.getRequestURI(), 200));
        sysLogEO.setHttpParamters(StringUtils.limitLength(String.valueOf(RequestUtils.getParameterMapSafe(request)), 500));
        sysLogEO.setHttpUserAgent(StringUtils.limitLength(userAgent, 200));
        sysLogEO.setHttpRemoteHost(RequestUtils.getClientIp(request));
        sysLogEO.setExecuteTime(executeTime);
        sysLogEO.setRemark(StringUtils.limitLength(remark, 2000));
        try {
            sysLogEO.setUserId(LoginUserUtils.getLoginUserIdWithoutFlush(CookieUtils.getAuthToken(request)));
        } catch (LoginInvalidException e) {
            // ignore
        }
        this.save(sysLogEO);
        return sysLogEO;
    }
}
