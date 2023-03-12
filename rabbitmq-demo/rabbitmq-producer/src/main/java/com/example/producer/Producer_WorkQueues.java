package com.example.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 张丽璇
 * @date 2023/3/11
 */
public class Producer_WorkQueues {
	public static void main(String[] args) throws IOException, TimeoutException {
		//使用简单模式完成消息传递
		//一般情况下都是通过工厂模式创建连接的
		//1.创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//2.配置信息（连接服务的信息）
		connectionFactory.setHost("106.12.132.227");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/test");
		connectionFactory.setUsername("test");
		connectionFactory.setPassword("test");
		//连接rabbitmq通过amqp和tls(transfer layer security传输层安全)有个默认的握手时间，设置握手超时时间
		connectionFactory.setConnectionTimeout(3000000);
		//3.创建连接
		Connection connection = connectionFactory.newConnection();
		//4.创建channel
		Channel channel = connection.createChannel();
		//5.简单模式下使用的是默认交换机
		//6.创建队列
		//queueDeclare(String queue, boolean durable, boolean exclusive,
		// boolean autoDelete, Map<String, Object> arguments)
		channel.queueDeclare("work_queues", true, false, false, null);
		//7.发送消息
		// basicPublish(String exchange, String routingKey, AMQP.BasicProperties props, byte[] body)

		for (int i = 0; i < 10; i++) {
			String body = i + "hello rabbitmq~~~";
			channel.basicPublish("", "work_queues", null, body.getBytes());
		}
		//8.释放资源
		channel.close();
		connection.close();
	}
}
