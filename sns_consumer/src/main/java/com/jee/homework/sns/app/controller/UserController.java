package com.jee.homework.sns.app.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jee.homework.sns.app.dto.RoleDto;
import com.jee.homework.sns.app.dto.UserDto;
import com.jee.homework.sns.app.service.impl.UserService;
import com.jee.homework.sns.app.vo.UserRegistVo;
import com.jee.homework.sns.common.constant.RoleConstants;
import com.jee.homework.sns.common.constant.StatusConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Reference
    private UserService userService;


    @PostMapping("/regist")
    @ApiOperation(value = "注册新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userRegistVo" ,value = "用户注册信息", dataType = "UserRegistVo")
    })
    public Long regist(@Valid @RequestBody UserRegistVo userRegistVo){
        //用户注册的时候只有一种角色--用户   刚注册时用户的状态也一定是 启用状态
        RoleDto roleDto = new RoleDto(RoleConstants.ROLE_USER_ID);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRegistVo,userDto);
        userDto.addRole(roleDto);
        userDto.setStatusId(StatusConstants.ENABLE_ID);
        return userService.addUser(userDto);
    }
    /**
     * 删除用户需要验证用户是不是管理员
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "管理员删除用户")
    @ApiImplicitParam(name = "id", value = "想要删除用户的id", required = true, paramType = "path")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "SUCCESS";
    }
    /**
     * 管理员禁用某一个账号
     */
    @PutMapping("/{id}/changeStatus")
    @ApiOperation(value = "管理员修改指定用户的状态")
    @ApiImplicitParam(name = "id", value = "用户的ID", required = true, paramType = "path")
    @PreAuthorize("hasRole('ADMIN')")
    public String changeUserStatus(@PathVariable("id") Long id){
        userService.changeStatus(id);
        return "SUCCESS";
    }
    /**
     * 用户收藏某一个帖子
     */
    @PutMapping("{userid}/like/{postid}")
    @ApiOperation(value = "用户收藏帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户ID", required = true,paramType = "path"),
            @ApiImplicitParam(name = "postid", value = "帖子ID",required = true,paramType = "path")
    })
    public String likePost(@PathVariable("userid") Long userid,@PathVariable("postid") Long postid){
        userService.likePost(userid,postid);
        return "SUCCESS";
    }

}
