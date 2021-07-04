package com.jee.homework.sns.app.controller;

import com.jee.homework.sns.app.security.AuthService;
import com.jee.homework.sns.app.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(tags = "权限管理")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Value("${jwt.header}")
    private String tokenheader;
    @RequestMapping(path ="/auth",method = RequestMethod.POST)
    @ApiOperation("获取令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userLoginVo", value = "用户登录信息", dataType = "UserLoginVo")
    })
    public String login(@RequestBody UserLoginVo userLoginVo) throws AuthenticationException {
        final String token = authService.login(userLoginVo.getName(),userLoginVo.getPassword());
        return token;
    }
    @RequestMapping(path = "/refreshAuth", method = RequestMethod.GET)
    @ApiOperation("刷新令牌")
    public String refresh(HttpServletRequest request){
        String oldToken = request.getHeader(tokenheader);
        return authService.refresh(oldToken);
    }

}
