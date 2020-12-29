package com.example.demo.controller;

import com.example.demo.aspect.SystemControllerLog;
import com.example.demo.domain.dto.DemoDto;
import com.example.demo.domain.entity.Demo;
import com.example.demo.domain.vo.Result;
import com.example.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: chenweilong
 * @Date: 2020/8/26
 * @Description:
 **/
@Api(tags = "controller模块类别名称")
@Controller
@ResponseBody
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private DemoService demoService;


    @ApiOperation("hello")
    @GetMapping("/hello")
    public Result getHello(){
        System.out.println("访问到这了");
        return Result.show("hello");
    }

    @SystemControllerLog(operationContent = "获取demo实例")
    @ApiOperation("获取demo实例")
    @GetMapping("/demo")
    public Result getDemo(@RequestParam Integer id){
        System.out.println("访问到这了");
        return Result.show(demoService.getDemo(id));
    }

    @ApiOperation("登录")
    @GetMapping("/login")
    public Result login(@RequestParam String userId, @RequestParam String username,
                        HttpServletRequest request, HttpSession session){
        // 设置进session
        request.getSession().setAttribute("userId",userId);
        request.getSession().setAttribute("username",username);

//        System.out.println(request.getSession().getAttribute("userId"));

//        session.setAttribute("userId",userId);

        return Result.show("啊啊啊"); 
    }

    @ApiOperation("设置demo实例")
    @PostMapping("/demo")
    public Result setDemo(@RequestBody DemoDto demoDto){
        System.out.println("设置demo实例");
        return Result.show("");
    }


}
