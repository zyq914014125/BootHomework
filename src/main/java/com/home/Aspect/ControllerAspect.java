package com.home.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 控制器切点
 * @author Mr.X
 **/
@Aspect
@Component
public class ControllerAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    //所有控制器增加切点
    @Pointcut("execution(public * com.home.module.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut() {}


    /**
     * @Description 环绕通知,
     * Proceedingjoinpoint 继承了 JoinPoint 。
     * 是在JoinPoint的基础上暴露出 proceed 这个方法。proceed很重要，这个是aop代理链执行的方法。
     * 且，仅当执行环绕的时Proceedingjoinpoint才允许被使用
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */

    @Around(value = "com.home.Aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug(proceedingJoinPoint.toString());
        //执行目标方法
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    /**
     * @Description
     * @param joinPoint
     */
    @Before(value = "com.home.Aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("请求来源：" + request.getRemoteAddr());
        LOGGER.debug("请求URL：" + request.getRequestURL().toString());
        LOGGER.debug("请求方式：" + request.getMethod());
        LOGGER.debug("响应方法：" +
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
    }
}
