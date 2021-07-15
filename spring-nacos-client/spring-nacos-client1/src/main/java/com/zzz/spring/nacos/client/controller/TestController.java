package com.zzz.spring.nacos.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@RestController
@RefreshScope
@RequestMapping("test")
public class TestController {

    @Value("${task.name}")
    private String taskName;

    @Autowired
    private Environment environment;

    @PostMapping("taskName")
    public String taskName() {
        return taskName;
    }


    @PostMapping("test1")
    public String test1(String code) {
        return code;
    }

    @PostMapping("test2")
    public Object test2(@RequestParam Map<String, Object> map) {
        return map.get("code");
    }

    @PostMapping("test3")
    public Object test3(@RequestBody String body) {
        return body;
    }
}
