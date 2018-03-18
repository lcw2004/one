package com.lcw.one.main.aop;

import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.sys.service.SysLogEOService;
import com.lcw.one.util.utils.RequestUtils;
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
import java.util.Arrays;

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

        // 执行方法
        long startTime = System.currentTimeMillis();
        Object resultObj;
        try {
            resultObj = joinPoint.proceed();
            logInDb(request, (System.currentTimeMillis() - startTime), "");
        } catch (Exception e) {
            logInDb(request, (System.currentTimeMillis() - startTime), e.toString());
            throw e;
        }

        logger.info("==================== WebLogAspect End ====================");
        return resultObj;
    }

    private void logInDb(HttpServletRequest request, long timeOut, String remark) {
        try {
            SysLogEO sysLogEO = sysLogEOService.save(request, timeOut, remark);
            logger.info("---- Http LogId: {}", sysLogEO.getLogId());
        } catch (Exception e) {
            logger.error("记录日志失败");
        }
    }

}
