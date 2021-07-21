package com.zzz.spring.demo.listener;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@Component
@EnableJms
public class TopicCustomer {


    @JmsListener(destination = "zzz123456", containerFactory = "topicListener")
    public void subscriber(String text) {
        System.out.println("消费者1111111111111111111111消费+" + text);
    }


}
