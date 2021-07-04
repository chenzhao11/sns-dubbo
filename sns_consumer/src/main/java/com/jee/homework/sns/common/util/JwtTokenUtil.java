package com.jee.homework.sns.common.util;

import com.jee.homework.sns.app.security.JwtUser;
import com.jee.homework.sns.common.constant.ResultCode;
import com.jee.homework.sns.common.exception.APIException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *jwt的工具类，注册成组件其他地方使用的时候方便直接注入使用
 *Author: zhaochen
 *Date: 2021/6/29
 */
@Component
public  class JwtTokenUtil  {
    @Value("${jwt.claim.key.username}")
    String usernameKey;
    @Value("${jwt.claim.key.role}")
    String roleKey;
    @Value("${jwt.claim.key.created}")
    String createdKey;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    public  String generateToken(String username) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(usernameKey, username);
        claims.put(createdKey, new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public  String getUsernameFormToken(String token){
        if(token != null){
            Claims claims = getClaimsFromToken(token);
            return  (String)claims.get(usernameKey);
        }else{
            throw new APIException(ResultCode.EMPTY_TOKEN,"token为空");
        }

    }
    public String refreshToken(String token){
        String newToken;
        try{
            final Claims claims = getClaimsFromToken(token);
            newToken = generateToken((String) claims.get(usernameKey));
        }catch (Exception e){
            newToken = null;
        }
        return newToken;
    }
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        Claims claims = getClaimsFromToken(token);
        final String username = (String) claims.get(usernameKey);
        final Date created = new Date((Long) claims.get(createdKey));
        final Date expiration = claims.getExpiration();
        //如果token中的用户名和 userdetails 中的数据名一样，并且token没有过期就是合法的token
        return (
                username.equals(user.getUsername())
                        && expiration.after(new Date()));
    }
    //判断能不能刷新token
    public Boolean canTokenBeRefreshed(String token) {
        Claims claims = getClaimsFromToken(token);
        final Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }
}
