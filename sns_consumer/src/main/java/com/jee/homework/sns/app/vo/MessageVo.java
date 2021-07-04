package com.jee.homework.sns.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@ApiModel("用户发送消息")
public class MessageVo {
    @ApiModelProperty("发送用户ID")
    @NotNull(message = "发送用户ID")
    private Long fromUserId;
    @ApiModelProperty("接收用户ID")
    @NotNull(message = "接收用户ID")
    private Long toUserId;
    @ApiModelProperty("消息内容")
    @NotNull(message = "消息内容不能为空")
    private String content;
}
