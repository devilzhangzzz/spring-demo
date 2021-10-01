package com.zzz.spring.demo.listener;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.lang.reflect.ParameterizedType;

/**
 * @author zzz
 */
@Slf4j
public abstract class AbstractMessageListener<T> {
    
    private Class<T> clazz;
    
    @SuppressWarnings("unchecked")
	public AbstractMessageListener() {
    		clazz = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    /**
     * 进行消息处理
     * @param message 消息内容
     */
    protected abstract void doBusiness(T message);
    
    /**
     * 接收消息
     * @param message
     * @param session
     * @author jnie
     * 2021年7月20日 下午2:47:13
     */
    public abstract void receiveQueueMessage(Message message, Session session) throws JMSException;

    /**
     *  提供默认的接收消息的处理流程实现
     *  默认 ActiveMQTextMessage
     * @param message
     * @param session
     * @author jnie
     * 2021年7月20日 下午2:47:27
     */
    protected void doReceiveQueueMessage(Message message, Session session){
        ActiveMQTextMessage activeMQTextMessage=(ActiveMQTextMessage)message;
        try {
            String json = activeMQTextMessage.getText();
            String queueName=activeMQTextMessage.getDestination().getPhysicalName();
            log.info("channel={},param={}",queueName, json);
            processMessage(activeMQTextMessage.getDestination().getPhysicalName(), json);
            log.info("channel={},处理完成", queueName);
        } catch (JMSException e) {
        	log.error("出现异常:{}", e);
        }
    }

    /**
     * 模版的消息处理
     * @param queue 队列名字
     * @param message 消息内容
     */
    protected void processMessage(String queue, String message) {
        try {
        	log.debug("{} receive MQ str = {}", queue,  message);
            T param = JSONUtil.toBean(message, clazz);
            log.info("实例化参数成功: 消息 {} 实例化为: {}",message,  param);
            doBusiness(param);
        } catch (Exception e) {
        	log.error("产生异常,通道:{},消息:{},异常信息:{}", queue, message, e);
        }
    }

}
