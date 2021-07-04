package com.jee.homework.sns.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class PostDto implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTiem;
    public Long userId;
    public Integer commentCount;
    public String content;
    private List<CommentDto> comments = new ArrayList<>();
}
