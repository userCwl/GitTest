package com.example.demo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenweilong
 * @Date: 2020/8/27
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoVo {


    private Integer id;

    private String username;

    private String password;



}
