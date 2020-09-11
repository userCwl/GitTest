package com.example.demo.aspect;

import com.example.demo.domain.entity.LogSystem;
import com.example.demo.mapper.LogSystemMapper;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author: chenweilong
 * @Date: 2020/8/31
 * @Description:
 **/
@Aspect
@Component
public class SystemLogAspect {

    private static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired
    private LogSystemMapper logSystemMapper;

    /**
     * Controller层切点    注解拦截
     */
//    @Pointcut("@annotation(com.example.demo.aspect.SystemControllerLog)")
    @Pointcut("within(com.example.demo.controller..*)")
    public void controllerAspect(){

    }

    /**
     * 前置通知
     * @param joinPoint
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        logger.info("进入切面前置通知");

        Object[] args = joinPoint.getArgs();
        for (Object o:
             args) {
            Class<?> aClass = o.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field:
                 fields) {
                String name = field.getName();
                if(name.equals("userId")){
                    // 进行日志
                    field.setAccessible(true);
                    String value = (String) field.get(o);
                    System.out.println( name + ": " + value);
                }

            }
        }


        // 获取controller简单类型数据
        /*//RequestContextHolder：持有上下文的Request容器,获取到当前请求的request
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest httpServletRequest = sra.getRequest();

        //这一步获取到的方法有可能是代理方法也有可能是真实方法
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();

        //通过真实方法获取该方法的参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = paramNames.getParameterNames(m);

        //获取连接点方法运行时的入参列表
        Object[] args = joinPoint.getArgs();

        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i]);
        }

        for (String s:
             params.keySet()) {
            if(s.equals("username")){
                System.out.println("找到了");
            }
            System.out.println("key: " + s + "; value: " + params.get(s));
        }*/


        /*Set<String> strings = params.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            if(params.get(iterator) instanceof String) {
                System.out.println("map: " + iterator + params.get(iterator.next()));
            }else{
                System.out.println("map: " + iterator + params.get(iterator.next()));
            }
        }*/


        /*logger.info("进入日志切面前置通知");
        String content = getControllerMethodOperationContent(joinPoint);

        // 获取request
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();

        HttpServletRequest  request = (HttpServletRequest )ra.resolveReference(RequestAttributes.REFERENCE_REQUEST);

//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
        // 获取session中的值
        String userId = (String)request.getSession().getAttribute("userId");
        Object username =  request.getSession().getAttribute("username");
        logger.info("userId: "+ userId);
        logger.info("username: "+ username);


        LogSystem logSystem = LogSystem.builder()
                .content(content)
                .userId("11")
                .username("11")
                .build();
        int insert = logSystemMapper.insert(logSystem);
        if(insert != 0){
            logger.info("操作记录成功");
        }*/
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("进入日志切面后置通知");
        String content = getControllerMethodOperationContent(joinPoint);

        // 获取request
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();

//        HttpServletRequest  request = (HttpServletRequest )ra.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取session中的值
        String userId = (String)request.getSession().getAttribute("userId");
        Object username =  request.getSession().getAttribute("username");
        logger.info("userId: "+ userId);
        logger.info("username: "+ username);


        LogSystem logSystem = LogSystem.builder()
                .content(content)
                .userId("11")
                .username("11")
                .build();
        int insert = logSystemMapper.insert(logSystem);
        if(insert != 0){
            logger.info("操作记录成功");
        }
    }

    /**
     * 处理完请求，返回内容
     * @param res
     * @throws Throwable
     */
    @AfterReturning(returning = "res",pointcut = "controllerAspect()")
    public void doAfterReturning(Object res)throws Throwable{

    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){

    }

    /**
     * 获取注解中操作内容的描述
     * @param joinPoint
     * @return
     */
    public static String getControllerMethodOperationContent(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        String value = annotation.value();

        return value;

        /*SystemControllerLog controllerLog = method.getAnnotation(SystemControllerLog.class);
        String operationContent = controllerLog.operationContent();
        return operationContent;*/
    }


}
