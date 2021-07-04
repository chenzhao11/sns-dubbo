package com.jee.homework.sns.app.service.impl;

import com.jee.homework.sns.app.dto.MessageDto;

public interface MessageService {

    /**
     * 新增消息
     * @param messageDto
     */
    public void saveMessage(MessageDto messageDto);
    /**
     * 删除指定id的消息
     */
    public void deleteMessage(Long id);

}
