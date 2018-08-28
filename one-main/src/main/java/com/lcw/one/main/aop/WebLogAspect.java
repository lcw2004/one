package com.lcw.one.main.aop;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.sys.service.SysLogEOService;
import com.lcw.one.util.constant.YesOrNoEnum;
import com.lcw.one.util.utils.*;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private SysLogEOService sysLogEOService;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController() {
    }

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    public void mappingAnnotations() {
    }

    @Order(1)
    @Around("(controller() || restController()) && mappingAnnotations()")
    public Object logHttpInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("==================== WebLogAspect Start ====================");

        // 记录日志前需要准备的数据
        long startTime = System.currentTimeMillis();
        String userId = LoginUserUtils.getLoginUserIdNoException(request);
        Exception exception = null;

        Object resultObj;
        try {
            // 执行方法
            resultObj = joinPoint.proceed();
        } catch (Exception e) {
            // 设置异常信息
            logger.error(e.getMessage(), e);
            exception = e;
            throw e;
        } finally {
            logInDb(request, joinPoint, (int) (System.currentTimeMillis() - startTime), userId, exception);
        }

        logger.info("==================== WebLogAspect End ====================");
        return resultObj;
    }

    private SysLogEO initFromRequest(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
        Map<String, String> headers = RequestUtils.getHeaderMap(request);
        SysLogEO sysLog = new SysLogEO();
        sysLog.setLogId(UUID.randomUUID());
        sysLog.setIsFail(YesOrNoEnum.NO.getValue());
        sysLog.setCreateTime(new Date());
        sysLog.setHttpMethod(request.getMethod());
        sysLog.setHttpUri(StringUtils.limitLength(request.getRequestURI(), 200));
        sysLog.setHttpParamters(StringUtils.limitLength(GsonUtil.t2Json(RequestUtils.getParameterMapSafe(request)), 500));
        sysLog.setHttpUserAgent(StringUtils.limitLength(headers.get("user-agent"), 200));
        sysLog.setHttpRemoteHost(RequestUtils.getClientIp(request));
        sysLog.setUserId(LoginUserUtils.getLoginUserIdNoException(request));
        sysLog.setClassName(joinPoint.getSignature().getDeclaringTypeName());
        sysLog.setMethodName(joinPoint.getSignature().getName());
        ApiOperation apiOperation = AspectHelper.getMethodAnnotation(joinPoint, ApiOperation.class);
        if (apiOperation != null) {
            sysLog.setOperationName(apiOperation.value());
        }
        return sysLog;
    }

    private void logInDb(HttpServletRequest request, ProceedingJoinPoint joinPoint, int executeTime, String userId, Exception exception) {
        try {
            SysLogEO sysLog = initFromRequest(request, joinPoint);

            // 设置执行时间
            sysLog.setExecuteTime(executeTime);

            // 如果未设置用户ID，则再设置一次（退出的时候，需要先保存用户ID才能正确设置到）
            if (StringUtils.isEmpty(sysLog.getUserId())) {
                sysLog.setUserId(userId);
            }

            // 记录异常信息
            if (exception != null) {
                sysLog.setRemark(Exceptions.getStackTraceAsString(exception));
                sysLog.setIsFail(YesOrNoEnum.YES.getValue());
            }

            sysLogEOService.save(sysLog);

            logger.info("---- Http LogId: {}", sysLog.getLogId());
        } catch (Exception e) {
            logger.error("记录日志失败：{}", e.getMessage());
        }
    }


}
