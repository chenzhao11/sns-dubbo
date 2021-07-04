package com.jee.homework.sns.app.service.impl;



/**
 *用户认证的服务
 *Author: zhaochen
 *Date: 2021/6/29
 */

public interface AuthService {

    public String login(String username, String password);

    public String refresh(String oldToken);

}