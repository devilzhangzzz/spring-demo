package com.zzz.spring.demo.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@Component
public class ToThirdCustomer extends AbstractMessageListener<String> {
    @Override
    protected void doBusiness(String message) {

    }

    @JmsListener(destination = "Consumer.yyy.VirtualTopic.hsom.message.receive.to.third.part")
    @Override
    public void receiveQueueMessage(Message message, Session session) throws JMSException {
        ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
        System.out.println("1:" + activeMQTextMessage.getText());

    }

    @JmsListener(destination = "VirtualTopic.hsom.message.receive.to.third.part")
    public void receiveQueueMessage2(Message message, Session session) throws JMSException {
        ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
        System.out.println("2:" + activeMQTextMessage.getText());

    }
}
