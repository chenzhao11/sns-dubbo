package com.jee.homework.sns.app.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jee.homework.sns.app.dto.CommentDto;
import com.jee.homework.sns.app.model.Comment;
import com.jee.homework.sns.app.model.Post;
import com.jee.homework.sns.app.model.User;
import com.jee.homework.sns.app.repository.CommentRepository;
import com.jee.homework.sns.app.repository.PostRepository;
import com.jee.homework.sns.app.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 10000,interfaceClass = CommentService.class)
@org.springframework.stereotype.Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private PostRepository postRepository;
    /**
     * 某个帖子新增评论
     */
    public void addComment(CommentDto commentDto){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        User user = userRepository.getById(commentDto.getUserId());
        Post post = postRepository.getById(commentDto.getPostId());
        comment.setPost(post);
        comment.setUser(user);
        userRepository.save(user);
        postRepository.save(post);
        commentRepository.save(comment);
    }
    /**
     * 删除指定评论
     */
    public void deleteCommnet(Long commentid){
        commentRepository.deleteById(commentid);
    }
}
