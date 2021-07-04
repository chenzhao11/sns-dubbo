package com.jee.homework.sns.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel("评论")
public class CommentVo {
    @ApiModelProperty(value = "所属帖子id")
    @NotNull(message = "帖子ID不能为空！")
    private Long postId;
    @ApiModelProperty("用户id")
    @NotNull(message = "用户ID不能为空！")
    private Long userId;
    @NotNull(message = "评论内容不能为空！")
    private String content;
}
