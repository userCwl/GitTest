package com.example.demo.domain.dto;

import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @Author: chenweilong
 * @Date: 2020/9/15
 * @Description:
 **/
public class WebResult {

    String code = "";
    String msg = "";
    Object data;
    Object attr;

    public static WebResult getSuccWebResult(String code, Object data){
        WebResult result = new WebResult();
        result.setCode(code);
        result.setMsg("success");
        if(data != null){
            result.setData(data);
        }
        return result;
    }
    public static WebResult getSuccWebResult(String code,String message, Object data){
        WebResult result = new WebResult();
        result.setCode(code);
        result.setMsg(message);
        if(data != null){
            result.setData(data);
        }
        return result;
    }

    public static WebResult getSuccWebResult(String code, Object data, Object attr){
        WebResult result = new WebResult();
        result.setCode(code);
        result.setMsg("success");
        if(data != null){
            result.setData(data);
        }
        result.setAttr(attr);
        return result;
    }


    public static WebResult getFailWebResult(String errCode, String errMsg){
        WebResult result = new WebResult();
        result.setCode(errCode);
        if(!StringUtils.isEmpty(errMsg)) {
            result.setMsg(errMsg);
        }
        return result;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String toJson(SerializerFeature... features){
        if(features == null){
            return JSON.toJSONString(this);
        }else{
            return JSON.toJSONString(this, features);
        }
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getAttr() {
        return attr;
    }

    public void setAttr(Object attr) {
        this.attr = attr;
    }

}
