package com.zzz.spring.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
public class TopicPersistentTest {


    @Test
    public void test1() throws Exception {
        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.25.22.28:61616");
        //从工厂中获取连接对象
        Connection connection = connectionFactory.createConnection();
        //连接MQ服务
        connection.start();
        //获得回话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session对象创建Topic
        Topic topic = session.createTopic("itheimaTopic");
        //通过session对象创建消息的发送者
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //通过session创建消息对象
        TextMessage message = session.createTextMessage("ping111");
        //发送消息
        producer.send(message);
        //关闭相关资源
        producer.close();
        session.close();
        connection.close();
    }


    @Test
    void test2() throws JMSException {
        String clientId = "client_id";

        // 连接到ActiveMQ服务器
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://172.25.22.28:61616");
        Connection connection = factory.createConnection();
        //客户端ID,持久订阅需要设置
        connection.setClientID(clientId);
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 创建主题
        Topic topic = session.createTopic("itheimaTopic");
        // 创建持久订阅,指定客户端ID。
        MessageConsumer consumer = session.createDurableSubscriber(topic,clientId);
        // 订阅接收方法
        consumer.setMessageListener(message -> {
            TextMessage tm = (TextMessage) message;
            try {
                System.out.println("Received message: " + tm.getText()+":"+tm.getStringProperty("property"));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

    }
}
