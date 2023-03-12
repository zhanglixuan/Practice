package com.example.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 张丽璇
 * @date 2023/3/11
 */
public class Producer_Routing {
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置参数
		factory.setHost("106.12.132.227");
		factory.setPort(5672);
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");
		//创建连接
		Connection connection = factory.newConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//创建交换机
		//exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable,
		// boolean autoDelete, boolean internal, Map<String, Object> arguments)
		//参数： 1.exchange 交换机名称
		//      2. type 交换机类型
		//           DIRECT("direct")：定向
		//           FANOUT("fanout")： 广播，发送消息到每一个与之绑定的队列
		//           TOPIC("topic") 通配符
		//           HEADERS("headers") 参数匹配
		//      3.durable 是否持久化
		//     4.internal 内部使用。 一般false
		//     5.arguments 参数
		String exchangeName = "test_direct_exchange";
		channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, null);
		//创建队列
		String queueName1 = "test_direct_queue1";
		String queueName2 = "test_direct_queue2";
		channel.queueDeclare(queueName1,true,false,false,null);
		channel.queueDeclare(queueName2,true,false,false,null);
		//绑定队列与交换机
		//queueBind(String queue, String exchange, String routingKey)
		// 参数： queue 队列名称
		//        exchange 交换机名称
		//        routingKey 路由键，绑定规则
		//           如果交换机的类型为fanout, routingKey设置为""
		//        arguments
		channel.queueBind(queueName1,exchangeName,"error");
		channel.queueBind(queueName2,exchangeName,"info");
		channel.queueBind(queueName2,exchangeName,"error");
		channel.queueBind(queueName2,exchangeName,"warning");
		//发送消息
		String body = "日志信息：张三调用了findAll方法...日志级别：info...";
		channel.basicPublish(exchangeName,"info",null,body.getBytes());
		//释放资源
		channel.close();
		channel.close();
	}
}
