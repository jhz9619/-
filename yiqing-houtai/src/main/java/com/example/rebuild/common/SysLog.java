package com.example.rebuild.common;

import java.lang.annotation.*;

// 定义系统日志注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
