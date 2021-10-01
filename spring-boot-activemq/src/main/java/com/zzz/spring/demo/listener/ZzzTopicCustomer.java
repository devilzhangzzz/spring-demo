package com.zzz.spring.demo.listener;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Component
public class ZzzTopicCustomer {

    @Bean(name = "zzzTopicListener")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> topicListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setClientId("zzzTopic");
        factory.setSubscriptionDurable(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }



    @JmsListener(destination = "zzzTopic", containerFactory = "zzzTopicListener")
    public void subscriber(String text) {
        System.out.println("消费+" + text);
    }



}
