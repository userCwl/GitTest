package com.example.demo.service.impl;

import com.example.demo.domain.entity.Demo;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenweilong
 * @Date: 2020/8/26
 * @Description:
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Demo getDemo(Integer id) {
        Demo demo = demoMapper.selectById(id);
        return demo;
    }
}
