package com.zzz.spring.nacos.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringNacosClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringNacosClient1Application.class, args);
    }

}
