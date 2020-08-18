package com.home.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Service Aop
 * @author Mr.X
 **/
@Aspect
@Component
public class  ServiceAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    //切点引入
    @Pointcut("@annotation(com.home.Aspect.ServiceAspectAnnoaction)")
    @Order(2)
    public void ServicePointcut(){ }

    @Around(value = "com.home.Aspect.ServiceAspect.ServicePointcut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug(proceedingJoinPoint.toString());
        //执行目标方法
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
}
