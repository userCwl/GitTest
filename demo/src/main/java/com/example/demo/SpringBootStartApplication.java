package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author: chenweilong
 * @Date: 2020/9/3
 * @Description:
 **/
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        // 指向原先用main方法执行的Application启动类
        return builder.sources(DemoApplication.class);
    }

}
