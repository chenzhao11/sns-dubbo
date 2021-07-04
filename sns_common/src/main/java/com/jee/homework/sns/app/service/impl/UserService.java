package com.jee.homework.sns.app.service.impl;

import com.jee.homework.sns.app.dto.RoleDto;
import com.jee.homework.sns.app.dto.StatusDto;
import com.jee.homework.sns.app.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * 新增用户
     * @Return 新增用户的ID
     */
    public Long addUser(UserDto userDto);
    /**
     * 新建角色
     */
    public Long addRole(RoleDto roleDto);
    /**
     * 删除用户
     * @param  Id 要删除用户的ID
     */
    public void deleteUser(Long Id);
    /**
     * 添加用户状态
     * @Param status_id  状态的ID
     */
    public void addStatus(StatusDto statusDto);
    /**
     * 根据用户名查找用户
     */

    public List<UserDto> getUserByUsername(String username);

    /**
     * 改变用户的状态，这里之前的数据库没有设计好应该直接用Boolean值，因为只有两种状态
     * @param id 要改变状态的用户的ID
     */
    public void changeStatus(Long id);
    /**
     *
     */
    public void likePost(Long userid,Long postid);
}
