package com.zzz.spring.demo.redis;

import lombok.Data;

/**
 * @Author: YuanZheng
 * @Description:
 * @Date: 2019/12/20 13:16
 * @Modifier:
 * @Version 1.0
 */
@Data
public class ServiceResult<T> {

    private String code;
    private String message;
    private T data;


    public static ServiceResult<String> ofSuccess() {
        return ofSuccess("");
    }

    public static <T> ServiceResult<T> ofSuccess(T data) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setMessage(ErrorCodeEnum.SUCCEED.getMessage());
        result.setCode(ErrorCodeEnum.SUCCEED.getCode());
        result.setData(data);
        return result;
    }

    public static ServiceResult<String> ofFailed(ErrorCodeEnum errorCodeEnum) {
        return ofFailed(errorCodeEnum, "");
    }

    public static <T> ServiceResult<T> ofFailed(ErrorCodeEnum errorCodeEnum, T data) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setMessage(errorCodeEnum.getMessage());
        result.setCode(errorCodeEnum.getCode());
        result.setData(data);
        return result;
    }

    public static ServiceResult<String> ofFailed(String message) {
        ServiceResult<String> result = new ServiceResult<>();

        result.setMessage(message);
        result.setCode("110");
        result.setData(null);
        return result;
    }

}
