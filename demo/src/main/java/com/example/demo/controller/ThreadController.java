package com.example.demo.controller;

import com.example.demo.domain.dto.DemoDto;
import com.example.demo.domain.dto.WebResult;
import com.example.demo.service.DemoService;
import com.example.demo.service.LongTimeAsyncCallService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @Author: chenweilong
 * @Date: 2020/9/15
 * @Description:
 **/
@Api(tags = "线程池")
@Controller
@ResponseBody
@RequestMapping("/thread")
public class ThreadController {

    @Resource
    private LongTimeAsyncCallService callService;

    private Long deferredTimeout;

    @Resource
    private DemoService demoService;

    @RequestMapping()
    @GetMapping("/query")
    public Object query(Integer id){
        DeferredResult<WebResult> deferredResult = new DeferredResult<>(deferredTimeout);
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                demoService.getDemo(id);
                return deferredResult;
            }
        };
        // 使用service创建的线程池，处理controller请求产生的一个个任务
        callService.handle(deferredResult,callable);
        return deferredResult;
    }


}
