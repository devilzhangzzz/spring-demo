package com.zzz.spring.demo.config;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@Configuration
public class ActiveMqConfig {


    @Value("${spring.application.name:default}")
    private String applicationName;


    @Bean(name = "topicListener")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> topicListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setClientId(IdUtil.randomUUID());
        factory.setSubscriptionDurable(true);
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

}
