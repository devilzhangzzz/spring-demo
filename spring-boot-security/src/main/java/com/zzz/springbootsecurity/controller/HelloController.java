package com.zzz.springbootsecurity.controller;

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
        return "hello world";
    }

}
