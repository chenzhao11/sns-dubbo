package com.jee.homework.sns.app.security;

import com.jee.homework.sns.app.security.JwtUser;
import com.jee.homework.sns.app.security.JwtUserService;
import com.jee.homework.sns.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *用户认证的服务
 *Author: zhaochen
 *Date: 2021/6/29
 */
@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserService jwtUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public String login(String username, String password) {
        //AbstractAuthenticationToken的子类只是为了存放账号密码，在后面验证成功过后authenticationManager生成的Authentication类中pricipal中包含所有的用户信息
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = jwtUserService.loadUserByUsername(username);
        final String token = tokenHead + jwtTokenUtil.generateToken(userDetails.getUsername());
        return token;
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFormToken(token);
        JwtUser user = (JwtUser) jwtUserService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token)){
            return tokenHead + jwtTokenUtil.refreshToken(token);
        }
        return null;
    }}

