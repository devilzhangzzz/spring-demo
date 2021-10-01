package com.zzz.spring.demo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@Component
public class ZzzCustomer {


    @JmsListener(destination = "zzz")
    public void subscriber(String text) {
        System.out.println("消费+" + text);
    }


}
