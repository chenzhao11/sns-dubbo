package com.jee.homework.sns.common.constant;


/**
 *
 *Author: zhaochen
 *Date: 2021/6/28
 */
public enum ResultCode {
    //可以理解成指定了一些实例，这些实例都有指定的属性
    SUCCESS(200,"success"),
    SERVER_INNER_ERRO(500,"服务器内部错误"),
    ARGUMENT_ERRO(1000,"参数错误"),
    USERNAME_NOTFOUND(1001,"未找到用户名"),
    AUTHTICATION_FAILED(1002,"用户认证失败"),
    RESULTPACK_ERRO(1003,"封装返回结果失败"),
    USERNAMENOTUNIC(1004,"用户名已存在"),
    STATUS_NOT_EXITS(1005,"非法用户状态"),
    EMPTY_TOKEN(1006,"token为空");

    private  int code;
    private String msg;
    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
    public int getCode(){
        return this.code;
    }
}
