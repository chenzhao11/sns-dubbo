package com.jee.homework.sns.common.base;

import com.jee.homework.sns.common.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResultVo {
    private int code;
    private String msg;
    private Object data;
    // 处理成功返回数据对象
    public ResultVo(Object data){
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }
}
