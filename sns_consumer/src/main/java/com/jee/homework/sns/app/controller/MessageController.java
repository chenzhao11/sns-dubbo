package com.jee.homework.sns.app.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jee.homework.sns.app.dto.MessageDto;
import com.jee.homework.sns.app.service.impl.MessageService;
import com.jee.homework.sns.app.vo.MessageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "消息管理")
@RequestMapping("/message")
public class MessageController {
    @Reference
    private MessageService messageService;
    @PostMapping("/send")
    @ApiOperation(value = "发送消息")
    @ApiImplicitParam(name = "messageVo",value = "发送的消息", dataType = "MessageVo")
    public String  postMessage(@RequestBody MessageVo messageVo){
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(messageVo,messageDto);
        messageService.saveMessage(messageDto);
        return "SUCCESS";
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除消息")
    @ApiImplicitParam(name = "id", value = "要删除的帖子的id", required = true, paramType = "path")
    public String deleteMessage(@PathVariable("id") Long id){
        messageService.deleteMessage(id);
        return  "SUCCESS";
    }
}
