package com.jee.homework.sns.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistVo {
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    @Size(min=2,max=12,message = "用户名字符长度为2到12个字符")
    private String name;
    @NotNull(message = "用户密码不能为空")
    @ApiModelProperty(value = "密码")
    @Size(min = 6,max = 12,message = "用户密码的长度应为6到12个字符")
    private String password;
    @NotNull(message = "用户的昵称不能为空")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
}
