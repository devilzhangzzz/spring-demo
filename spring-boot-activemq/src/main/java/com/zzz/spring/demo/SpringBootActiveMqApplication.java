package com.zzz.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class SpringBootActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMqApplication.class, args);
	}

}
