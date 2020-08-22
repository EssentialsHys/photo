package org.ynu.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MQTest {

	//@Test
	public void testSpringMQ1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmsTemplate template = context.getBean(JmsTemplate.class);
		ActiveMQTopic queue = context.getBean(ActiveMQTopic.class);
		//消息队列对象
		template.send(queue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage test = session.createTextMessage("This is springMQ Test!");
				return test;
			}
		});
	}
		
		
	//@Test
	public void testSpringMQ() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmsTemplate template = context.getBean(JmsTemplate.class);
		ActiveMQQueue queue = context.getBean(ActiveMQQueue.class);
		//消息队列对象
		template.send(queue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage test = session.createTextMessage("This is springMQ Test!");
				return test;
			}
		});
	}
	
	
	
	
	/**
	 * 点对点消息的接收
	 * @throws JMSException 
	 */
	//@Test
	public void testTopicConsumer() throws JMSException {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://59.110.62.54:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		connection.start();
//		第四步：使用Connection对象创建一个Session对象。
		//参数 1：是否开启事务，分布式事务，一般不开启。  参数2：响应类型，设置为自定响应
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
		Destination destination = session.createTopic("igeek-topic");//不要忘记设置队列的名称
//		第六步：使用Session对象创建一个consumer对象。
		MessageConsumer consumer = session.createConsumer(destination);
//		第七步：接收消息。通过consumer设置一个监听器，随时监听
		consumer.setMessageListener(new MessageListener() {//使用匿名内部类实现一个监听器
			@Override
			public void onMessage(Message message) {
//					第八步：打印消息。
				TextMessage msg = (TextMessage)message;
				try {
					System.out.println("queue接收端，接收到消息[3]:"+(msg.getText()));
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//让程序暂停
		try {
			System.out.println("queue开始监听[3]....");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		第九步：关闭资源。
		consumer.close();
		session.close();
		connection.close();
	}

	
	
	
	
	/**
	 * 点对点消息的发送
	 * @throws JMSException 
	 */
	//@Test
	public void testTopicProducer() throws JMSException {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://59.110.62.54:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		connection.start();
//		第四步：使用Connection对象创建一个Session对象。
		//参数 1：是否开启事务，分布式事务，一般不开启。  参数2：响应类型，设置为自定响应
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
		Destination destination = session.createTopic("igeek-topic");//不要忘记设置队列的名称
//		第六步：使用Session对象创建一个Producer对象。
		MessageProducer producer = session.createProducer(destination);
//		第七步：创建一个Message对象，创建一个TextMessage对象。
		TextMessage textMessage = session.createTextMessage("这是我发送的测试消息! zheshiwode ceshi xiaoxi!");
//		第八步：使用Producer对象发送消息。
		System.out.println("topic:发送消息。。。。");
		producer.send(textMessage);
//		第九步：关闭资源。
		producer.close();
		session.close();
		connection.close();
	}
	
	
	
	/**
	 * 点对点消息的接收
	 * @throws JMSException 
	 */
	//@Test
	public void testQueueConsumer() throws JMSException {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://59.110.62.54:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		connection.start();
//		第四步：使用Connection对象创建一个Session对象。
		//参数 1：是否开启事务，分布式事务，一般不开启。  参数2：响应类型，设置为自定响应
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
		Destination destination = session.createQueue("test-queue");//不要忘记设置队列的名称
//		第六步：使用Session对象创建一个consumer对象。
		MessageConsumer consumer = session.createConsumer(destination);
//		第七步：接收消息。通过consumer设置一个监听器，随时监听
		consumer.setMessageListener(new MessageListener() {//使用匿名内部类实现一个监听器
			@Override
			public void onMessage(Message message) {
//					第八步：打印消息。
				TextMessage msg = (TextMessage)message;
				try {
					System.out.println("queue接收端，接收到消息:"+(msg.getText()));
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//让程序暂停
		try {
			System.out.println("queue开始监听....");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		第九步：关闭资源。
		consumer.close();
		session.close();
		connection.close();
	}
	
	

	/**
	 * 点对点消息的发送
	 * @throws JMSException 
	 */
	//@Test
	public void testQueueProducer() throws JMSException {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://59.110.62.54:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		connection.start();
//		第四步：使用Connection对象创建一个Session对象。
		//参数 1：是否开启事务，分布式事务，一般不开启。  参数2：响应类型，设置为自动响应
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
		Destination destination = session.createQueue("test-queue");//不要忘记设置队列的名称
//		第六步：使用Session对象创建一个Producer对象。
		MessageProducer producer = session.createProducer(destination);
//		第七步：创建一个Message对象，创建一个TextMessage对象。
		TextMessage textMessage = session.createTextMessage("这是我发送的测试消息!");
//		第八步：使用Producer对象发送消息。
		System.out.println("发送消息。。。。");
		producer.send(textMessage);
//		第九步：关闭资源。
		producer.close();
		session.close();
		connection.close();
	}

}
