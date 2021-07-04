package com.jee.homework.sns.app.security;

import com.jee.homework.sns.app.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 *将从数据库按照用户名查出来的userdto转换成jwtuser对象的工厂类
 *Author: zhaochen
 *Date: 2021/6/29
 */   
public final class JwtUserFactory {
    private JwtUserFactory() {
    }
    public static JwtUser transform(UserDto userDto) {
        return new JwtUser(
                userDto.getId().toString(),
                userDto.getName(),
                userDto.getPassword(),
                mapToGrantedAuthorities(userDto.getRoleNames())
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
