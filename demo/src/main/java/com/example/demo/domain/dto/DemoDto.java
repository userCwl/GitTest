package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenweilong
 * @Date: 2020/9/1
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoDto {

    private String content;

    private String userId;

    private String username;

}
