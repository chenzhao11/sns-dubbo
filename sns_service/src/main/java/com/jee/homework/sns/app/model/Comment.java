package com.jee.homework.sns.app.model;

import com.jee.homework.sns.common.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 评论类
 * @author 赵陈
 */
@Entity
@Table(name = "comment")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {


    /**
     * 与这个评论相关联的帖子id
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 评论的创建者id
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;


}