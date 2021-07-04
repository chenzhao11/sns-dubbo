package com.jee.homework.sns.app.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jee.homework.sns.app.dto.UserDto;
import com.jee.homework.sns.app.service.impl.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *验证token是把token中的信息和数据库的信息进行对比，这个service主要是获取数据库中对应用户名的用户信息
 *Author: zhaochen
 *Date: 2021/6/29
 */   

@Service
public class JwtUserService implements UserDetailsService {
    @Reference
    UserService userService;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDto> userDtos = userService.getUserByUsername(username);
        UserDto userDto = userDtos.size() > 0 ? userDtos.get(0) : null;

        if (userDto == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.transform(userDto);
        }
    }
}
