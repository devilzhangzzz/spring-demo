package com.zzz.spring.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;

@Component
@Slf4j
public class PayResultTopicListener extends AbstractMessageListener<CurrentRoleDTO> {

	@Override
	protected void doBusiness(CurrentRoleDTO param) {
		log.info("i am receice topic1 :{}", param);
	}



	@Override
	@JmsListener(destination = "topic.hsom.pay.result", containerFactory = "topicListener")
	public void receiveQueueMessage(Message message, Session session) {
		super.doReceiveQueueMessage(message, session);
	}
	
	

}
