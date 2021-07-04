package com.jee.homework.sns.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
public class MessageDto implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTiem;
    public Long fromUserId;
    public Long toUserId;
    public String content;
}
