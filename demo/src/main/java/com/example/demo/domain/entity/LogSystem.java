package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author: chenweilong
 * @Date: 2020/8/31
 * @Description:
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("log_system")
public class LogSystem {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "content")
    private String content;

    @TableField(value = "userId")
    private String userId;

    @TableField(value = "username")
    private String username;

    @TableField(value = "createTime")
    private Timestamp createTime;

    @TableField(value = "updateTime")
    private Timestamp updateTime;

}
