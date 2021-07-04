package com.jee.homework.sns.app.model;

import com.jee.homework.sns.common.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 消息类
 * @author 赵陈
 */
@Entity
@Table(name = "message")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {

    /**
     * 消息发送用户ID
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "from_user_id")
    public User fromUser;

    /**
     * 消息接收用户ID
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "to_user_id")
    public User toUser;


    /**
     * 消息的内容
     */
    @Column(name = "content")
    public String content;


}