package com.example.demo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: chenweilong
 * @Date: 2020/8/26
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int error;// 状态码
    private String msg;// 状态描述
    private Object data;// 返回数据

    public static Result show(Object data){
        // data为空 或者data是List类型但是无元素
        if(data == null || (((data instanceof List)) && ((List)data).size() == 0) ){
            return new Result(2,"no_data",data);
        }
        return new Result(1,"normal",data);
    }

}
