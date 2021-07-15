package com.zzz.spring.nacos.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.cloud.bootstrap.BootstrapApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@SpringBootApplication
public class TestMain {

    @Autowired
    private Environment environment;

    @Configuration(proxyBeanMethods = false)
    protected static class Empty {

    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Empty.class)
                .bannerMode(Banner.Mode.OFF).web(WebApplicationType.NONE);
        builder.application()
                .setListeners(Arrays.asList(new BootstrapApplicationListener(),
                        new ConfigFileApplicationListener()));
         builder.run();
    }
}
