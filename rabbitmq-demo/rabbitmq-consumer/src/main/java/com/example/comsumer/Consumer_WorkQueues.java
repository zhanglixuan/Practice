package com.example.comsumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 张丽璇
 * @date 2023/3/11
 */
public class Consumer_WorkQueues {
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
		channel.queueDeclare("hello_word", true, false, false, null);
		//7.发送消息
		//basicConsume(String queue, boolean autoAck, Consumer callback)
		//参数：1.queue：队列名称
		//     2.autoAck 是否自动确认（即自动回复，消费者一旦收到消息，就会告诉mq消息收到了）
		//     3.callback 回调对象
		Consumer consumer = new DefaultConsumer(channel){
			//回调方法：当收到消息，会自动执行该方法
			//参数：consumerTag 消费标识（消费编码）
			//envelope  获取一些信息，如交换机、路由key...
			//properties 配置信息
			//body  数据
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				// System.out.println("consumerTag="+consumerTag);
				// System.out.println("envelope="+envelope);
				// System.out.println("properties="+properties);
				System.out.println("body="+new String(body));
			}
		};
		channel.basicConsume("work_queues",true,consumer);
		//释放资源？不需要
	}
}
