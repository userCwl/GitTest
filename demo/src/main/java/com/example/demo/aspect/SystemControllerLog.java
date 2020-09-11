package com.example.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: chenweilong
 * @Date: 2020/8/31
 * @Description:    日志记录
 **/
@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {
    /**
     * 操作内容
     * @return
     */
    String operationContent() default "";
}
