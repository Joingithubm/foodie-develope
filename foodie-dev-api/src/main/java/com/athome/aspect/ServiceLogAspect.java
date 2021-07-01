package com.athome.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/6/29 17:55
 * @Version 1.0
 */
@Component
@Aspect
public class ServiceLogAspect {

    private static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 使用环绕通知，在方法前后记录时间
     * @param joinPoint
     * @return
     */
    @Around(value = "execution(* com.athome.service.impl.*.*(..))")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("开始执行{},参数：{}",joinPoint.getSignature(), joinPoint.getArgs());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        long takeTime = end - start;

        if (takeTime > 3000){
            log.error("方法:{},参数：{}耗时：{}毫秒",joinPoint.getSignature(), joinPoint.getArgs(),takeTime);
        } else if (takeTime>2000){
            log.warn("方法:{},参数：{}耗时：{}毫秒",joinPoint.getSignature(), joinPoint.getArgs(),takeTime);
        }else {
            log.info("方法:{},参数：{}耗时：{}毫秒",joinPoint.getSignature(), joinPoint.getArgs(),takeTime);
        }

        return result;
    }


}
