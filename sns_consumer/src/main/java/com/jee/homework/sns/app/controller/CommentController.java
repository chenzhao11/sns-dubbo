package com.jee.homework.sns.app.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jee.homework.sns.app.dto.CommentDto;
import com.jee.homework.sns.app.dto.MessageDto;
import com.jee.homework.sns.app.service.impl.CommentService;
import com.jee.homework.sns.app.vo.CommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Api(tags = "评论管理")
@RequestMapping("/comment")
@RestController
public class CommentController {
    @Reference
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperation("新增评论")
    @ApiImplicitParam(name = "commentVo",value = "用户评论",dataType = "CommentVo")
    public String addComment(@RequestBody CommentVo commentVo){
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentVo,commentDto);
        commentService.addComment(commentDto);
        return "SUCCESS";
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除指定id的评论")
    @ApiImplicitParam(name = "id",value = "评论id",required = true,paramType = "path")
    public String deleteComment(@PathVariable("id") Long id){
        commentService.deleteCommnet(id);
        return "SUCCESS";
    }

}
