package com.zzz.spring.nacos.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello2Controller {

    @RequestMapping("hello2")
    public String hello2() {
        return "hello2";
    }

}
