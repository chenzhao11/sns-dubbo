package com.jee.homework.sns.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

@ApiModel("用户登录表单")
public class UserLoginVo {
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @NotNull(message = "用户密码不能为空")
    @ApiModelProperty(value = "用户密码")
    private String password;
}
