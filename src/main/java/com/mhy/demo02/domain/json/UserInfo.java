package com.mhy.demo02.domain.json;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "用户基本信息 用户json存储")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @ApiModelProperty("姓名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("余额")
    private Integer balance;

}
