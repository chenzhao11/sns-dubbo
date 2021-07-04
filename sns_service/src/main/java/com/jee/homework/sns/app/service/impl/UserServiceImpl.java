package com.jee.homework.sns.app.service.impl;

import com.jee.homework.sns.app.dto.RoleDto;
import com.jee.homework.sns.app.dto.StatusDto;
import com.jee.homework.sns.app.dto.UserDto;
import com.jee.homework.sns.app.model.Role;
import com.jee.homework.sns.app.model.Status;
import com.jee.homework.sns.app.model.User;
import com.jee.homework.sns.app.repository.*;
import com.jee.homework.sns.common.constant.ResultCode;
import com.jee.homework.sns.common.constant.StatusConstants;
import com.jee.homework.sns.common.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 10000,interfaceClass = UserService.class)
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusRepository  statusRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MessageRepository messageRepository;
    /**
     * 新增用户
     * @Return 新增用户的ID
     */
    public Long addUser(UserDto userDto){
        //先判断有没有同名的账号，如果有抛出异常
        int count = userRepository.countUserByName(userDto.getName());
        if(count > 0){
            throw new APIException(ResultCode.USERNAMENOTUNIC,"用户名已存在");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User user=new User();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        //把UserDTO对象转换成User对象
        BeanUtils.copyProperties(userDto,user);
        //把roles列表中的RoleDTO类型转换成Role类型
        List<Role> roles = new ArrayList<>();
        for(RoleDto roleDto : userDto.getRoles()){
            Role role = new Role();
            BeanUtils.copyProperties(roleDto,role);
            roles.add(role);
        }
        user.setRoles(roles);
        //把StatusDTO转换成Status类型
        Status status = new Status();
        status.setId(userDto.getStatusId());
        user.setStatus(status);
        return userRepository.save(user).getId();
    }
    /**
     * 新建角色
     */
    public Long addRole(RoleDto roleDto){
        Role role=new Role();
        BeanUtils.copyProperties(roleDto,role);
        return roleRepository.save(role).getId();
    }
    /**
     * 删除用户
     * @param  Id 要删除用户的ID
     */
    public void deleteUser(Long Id){
        //外键约束删除用户之前需要处理

        messageRepository.deleteByUserId(Id);
        userRepository.deleteById(Id);

    }
    /**
     * 添加用户状态
     * @Param status_id  状态的ID
     */
    public void addStatus(StatusDto statusDto){
        Status status =new Status();
        BeanUtils.copyProperties(statusDto,status);
        log.warn("userservice中：");
        log.warn(status.toString());
        log.warn("原来的是：");
        log.warn(statusDto.toString());
        statusRepository.save(status);
    }
    /**
     * 根据用户名查找用户
     */
    @Cacheable(cacheNames="user",key = "#username")
    public List<UserDto> getUserByUsername(String username){
        List<User> users = userRepository.findByName(username);
        //把User转换成UserDto
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: users){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            //把roles列表中的Role类型转换成RoleDto类型
            for(Role role : user.getRoles()){
                RoleDto roleDto = new RoleDto();
                BeanUtils.copyProperties(role,roleDto);
                userDto.getRoles().add(roleDto);
            }
            //Status转换成StatusDto
            StatusDto statusDto = new StatusDto();
            userDto.setStatusId(user.getStatus().getId());
            userDtos.add(userDto);
        }
        return  userDtos;

    }

    /**
     * 改变用户的状态，这里之前的数据库没有设计好应该直接用Boolean值，因为只有两种状态
     * @param id 要改变状态的用户的ID
     */
    public void changeStatus(Long id) {
        //先得到原来的转态再根据需要转换成另一种转态
        User user = userRepository.getById(id);
        log.error(user.toString());
        Long status = user.getStatus().getId();

        if(status != StatusConstants.ENABLE_ID){

//            userRepository.updateUserStatus(id, StatusConstants.DISABLE_ID);
        }
        else if(status != StatusConstants.DISABLE_ID){
//            userRepository.updateUserStatus(id, StatusConstants.ENABLE_ID);
        }else{
            throw new APIException(ResultCode.STATUS_NOT_EXITS,"没有输入的用户状态！");
        }

    }
    /**
     *
     */
    public void likePost(Long userid,Long postid){

    }
}
