package com.example.demo.aspect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: chenweilong
 * @Date: 2020/8/31
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogSystem2 /*implements Serializable*/ {

//    private static final long serialVersionUID = 1L;

    private Integer id;

    private String content;

    private String userId;

    private String username;

    private Timestamp createTime;

    private Timestamp updateTime;

}
