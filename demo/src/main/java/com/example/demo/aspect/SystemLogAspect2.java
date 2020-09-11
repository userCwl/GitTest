package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: chenweilong
 * @Date: 2020/9/3
 * @Description:
 **/
@Aspect
@Component
public class SystemLogAspect2 {

    private static Logger logger = LoggerFactory.getLogger(SystemLogAspect2.class);

    @Pointcut("@annotation(com.example.demo.aspect.SystemControllerLog)")
    public void controllerAspect(){

    }

}
