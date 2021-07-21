package com.zzz.spring.demo.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 2144016980711260982L;

    private String code;

    private String message;


    private T data;

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<>("200", "成功", data);
    }




}
