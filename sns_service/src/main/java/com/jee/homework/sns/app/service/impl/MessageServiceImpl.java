package com.jee.homework.sns.app.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jee.homework.sns.app.dto.MessageDto;
import com.jee.homework.sns.app.model.Message;
import com.jee.homework.sns.app.model.User;
import com.jee.homework.sns.app.repository.MessageRepository;
import com.jee.homework.sns.app.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 10000,interfaceClass = MessageService.class)
@org.springframework.stereotype.Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 新增消息
     * @param messageDto
     */
    public void saveMessage(MessageDto messageDto){
        Message message =  new Message();
        User formUser = userRepository.getById(messageDto.getFromUserId());
        User toUser = userRepository.getById(messageDto.getToUserId());
        BeanUtils.copyProperties(messageDto,message);
        message.setFromUser(formUser);
        message.setToUser(toUser);
        userRepository.save(formUser);
        userRepository.save(toUser);
        messageRepository.save(message);

    }
    /**
     * 删除指定id的消息
     */
    public void deleteMessage(Long id){
        messageRepository.deleteById(id);
    }

}
