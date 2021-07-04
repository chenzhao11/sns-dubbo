package com.jee.homework.sns.app.service.impl;

import com.jee.homework.sns.app.dto.PostDto;

import java.util.List;

public interface PostService {

    /**
     * 查找所有的评论信息
     */
    public List<PostDto> findAll();
    /**
     * 新增帖子
     */
    public Long addPost(PostDto postDto);

    /**
     * 删除指定id的帖子
     * @param id
     */
    public void deletePost(Long id) ;
}
