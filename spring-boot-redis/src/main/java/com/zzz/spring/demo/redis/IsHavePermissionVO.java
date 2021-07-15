package com.zzz.spring.demo.redis;

import lombok.Data;

import java.util.Collection;

/**
 * 是否具有权限vo
 *
 * @author zhangzhizhong
 */
@Data
public class IsHavePermissionVO {

    private Collection<String> roles;

    private String url;

    private String method;

    private String module;
}
