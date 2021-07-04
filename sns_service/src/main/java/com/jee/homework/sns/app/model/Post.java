package com.jee.homework.sns.app.model;

import com.jee.homework.sns.common.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 帖子
 * @author 赵陈
 */
@Entity
@Table(name = "post")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post  extends BaseEntity {


    /**
     * 帖子创建者
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User user;


    /**
     * 帖子的评论总数
     */
    @Column(name = "comment_count")
    public Integer commentCount;

    /**
     * 帖子的内容
     */
    @Column(name = "content")
    public String content;

    /**
     * 帖子对应的评论
     */
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;




}