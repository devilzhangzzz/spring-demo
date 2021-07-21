package com.zzz.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMqApplication.class, args);
	}

}
