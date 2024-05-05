package com.mhy.demo02.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mhy.demo02.domain.json.UserInfo;
import com.mhy.demo02.enums.UserStatus;
import lombok.Data;

@Data
@TableName(value = "user",autoResultMap = true)
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;

    private Integer balance;

    @TableField(exist = false)
    private String age;

    @TableField(exist = false)
    private String address;

    @TableField(exist = false)
    private String order;

    private UserStatus status;

}
