package com.zzz.spring.oauth2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author zhangzhizhong
 */
@RestController

public class HelloController {


    @GetMapping("hello")
    public String hello() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "hello world";
    }

}
