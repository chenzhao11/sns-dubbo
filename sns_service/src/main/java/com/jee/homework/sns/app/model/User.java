package com.jee.homework.sns.app.model;

import com.jee.homework.sns.common.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 用户类
 * @author 赵陈
 */
@Entity
@Table(name = "user")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {


    /**
     *用户的角色
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    /**
     * 用户当前状态 禁用 0  启用 1
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;


    /**
     * 用户头像地址
     */
    @Column(name = "head_pic_url")
    private String headPicUrl;

    /**
     * 用户名
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;
    /**
     * 用户喜欢的post
     */
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_like_post",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "post_id")})
    private List<Post> likePosts;

    /**
     * 用户创建的评论public List<Post> likePosts;
     */
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    /**
     * 用户创建的帖子
     */
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id")
    private List<Post> posts;

}