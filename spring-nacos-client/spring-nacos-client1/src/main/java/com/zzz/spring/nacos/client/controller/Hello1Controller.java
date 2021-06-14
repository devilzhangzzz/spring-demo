package com.zzz.spring.nacos.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello1Controller {

    @RequestMapping("hello1")
    public String hello1() {
        return "hello1";
    }

}
