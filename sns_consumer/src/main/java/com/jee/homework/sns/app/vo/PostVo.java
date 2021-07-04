package com.jee.homework.sns.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("用户新增的帖子")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostVo {
    @ApiModelProperty(value = "创建用户的id")
    @NotNull(message = "不能没有创建用户！")
    public Long userId;
    @NotNull(message ="内容不能为空！")
    @ApiModelProperty(value = "贴子内容")
    public String content;
    private Date createTime;
    private Date updateTiem;
    public Integer commentCount;
}
