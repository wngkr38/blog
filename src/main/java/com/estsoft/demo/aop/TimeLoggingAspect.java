package com.estsoft.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeLoggingAspect {
    @Pointcut("execution(* com.estsoft.demo.blog.service..*(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long startTimeMs = System.currentTimeMillis();
        try{
            return joinPoint.proceed();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }finally {
            long endTimeMs = System.currentTimeMillis();
            log.info("메소드 실행 시간: {}ms", endTimeMs - startTimeMs);
        }
    }
}
