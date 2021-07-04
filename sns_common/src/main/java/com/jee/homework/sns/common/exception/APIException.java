package com.jee.homework.sns.common.exception;


import com.jee.homework.sns.common.constant.ResultCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException{
    private int code;
    private String msg;

    //传入code，调用code对应的msg消息
    public APIException(ResultCode code, String msg){
        super(msg);
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
    //不传入code返回默认的错误编码以及提示信息
    public APIException(String msg){
        super(msg);
        this.code = ResultCode.SERVER_INNER_ERRO.getCode();
        this.msg = ResultCode.SERVER_INNER_ERRO.getMsg();
    }
}
