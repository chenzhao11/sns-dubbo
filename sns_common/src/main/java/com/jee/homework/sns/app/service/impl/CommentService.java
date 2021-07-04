package com.jee.homework.sns.app.service.impl;

import com.jee.homework.sns.app.dto.CommentDto;

public interface CommentService {

    /**
     * 某个帖子新增评论
     */
    public void addComment(CommentDto commentDto);
    /**
     * 删除指定评论
     */
    public void deleteCommnet(Long commentid);
}
