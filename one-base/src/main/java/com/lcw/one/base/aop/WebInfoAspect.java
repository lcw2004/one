package com.lcw.one.base.aop;

import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.util.utils.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebInfoAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebInfoAspect.class);

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

        // 接收到请求，记录请求内容
        logger.info("==================== WebLogAspect Start ====================");
        logger.info("---- {} : {}", request.getMethod(), request.getRequestURL().toString());
        logger.info("---- Http Headers : {}", RequestUtils.getHeaderMap(request));
        logger.info("---- Http Parameters : {}", RequestUtils.getParameterMap(request));
        logger.info("---- Execute Method : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info("---- Execute Arguments : {}", Arrays.toString(joinPoint.getArgs()));

        // 执行方法
        long startTime = System.currentTimeMillis();
        Object resultObj = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logger.info("---- Execute Result : [{}]", String.valueOf(resultObj));
        logger.info("---- Time : {}ms", endTime - startTime);
        logger.info("==================== WebLogAspect End ====================");
        return resultObj;
    }


}
