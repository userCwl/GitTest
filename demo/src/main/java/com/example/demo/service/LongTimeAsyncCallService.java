package com.example.demo.service;

import com.example.demo.domain.dto.WebResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: chenweilong
 * @Date: 2020/9/15
 * @Description:
 **/
@Service
public class LongTimeAsyncCallService {
    // 创建可缓存的线程池
    private ExecutorService cacheExecutor = Executors.newCachedThreadPool();

    // 服务器关闭或者销毁servlet的时候会执行destroy方法告知使用者，准备销毁这个servlet
    // 用于关闭线程
    @PreDestroy
    public void destroyExecutor(){
        cacheExecutor.shutdown();
    }

    // Callable 一个个请求变成任务
    // DeferredResult   将请求线程与后台执行线程分离，异步
    public void handle(DeferredResult deferredResult, Callable callback){
        cacheExecutor.submit(callback);
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                WebResult result = WebResult.getFailWebResult("-1","server time out");
                deferredResult.setResult(result);
            }
        });
    }
}
