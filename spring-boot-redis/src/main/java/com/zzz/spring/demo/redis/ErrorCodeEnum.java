package com.zzz.spring.demo.redis;

import lombok.Getter;

/**
 * @Author: YuanZheng
 * @Description:
 * @Date: 2019/12/20 13:19
 * @Modifier:
 * @Version 1.0
 */
@Getter
public enum ErrorCodeEnum {

    SUCCEED("200", "成功"),
    FAILED("110", "失败");

    private String code;
    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
