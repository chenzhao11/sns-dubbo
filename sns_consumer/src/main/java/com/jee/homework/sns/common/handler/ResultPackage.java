package com.jee.homework.sns.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jee.homework.sns.common.base.ResultVo;
import com.jee.homework.sns.common.constant.ResultCode;
import com.jee.homework.sns.common.exception.APIException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *将数据封装以统一的格式返回给前端
 *Author: zhaochen
 *Date: 2021/6/28
 */
//要加上basePackage限制不然会影响swagger的使用
@RestControllerAdvice(basePackages = "com.jee.homework.sns")
public class ResultPackage implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !(methodParameter.getParameterType().isAssignableFrom(ResultVo.class));
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResultVo(data));
            } catch (JsonProcessingException e) {
                throw new APIException(ResultCode.RESULTPACK_ERRO, e.getMessage());
            }
        }
        return new ResultVo(data);
    }
}
