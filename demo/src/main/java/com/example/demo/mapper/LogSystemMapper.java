package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.aspect.LogSystem2;
import com.example.demo.domain.entity.LogSystem;
import org.springframework.stereotype.Repository;

/**
 * @Author: chenweilong
 * @Date: 2020/8/31
 * @Description:
 **/
@Repository
public interface LogSystemMapper extends BaseMapper<LogSystem> {
}
