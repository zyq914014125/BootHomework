package com.home.Aspect;

import java.lang.annotation.*;

/**
 * @Target:元注解:修饰对象范围
 * @Documented 公共
 * @Retention 生效范围
 * @author Mr.X
 **/
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAspectAnnoaction {
}
