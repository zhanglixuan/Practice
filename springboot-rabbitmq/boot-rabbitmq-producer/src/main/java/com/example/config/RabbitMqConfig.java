package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author 张丽璇
 * @date 2023/3/12
 */
@Configuration
public class RabbitMqConfig {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static final String EXCHANGE_NAME = "boot_topic_exchange";
	public static final String QUEUE_NAME = "boot_topic_queue";
	public static final String DLX_EXCHANGE_NAME = "boot_topic_dlx_exchange";
	public static final String DLX_QUEUE_NAME = "boot_topic_dlx_queue";

	@Bean("dlx_exchange")
	public Exchange dlxExchange(){
		return ExchangeBuilder.topicExchange(DLX_EXCHANGE_NAME).durable(true).build();

	}
	@Bean("dlx_queue")
	public Queue dlxQueue(){
		return QueueBuilder.durable(DLX_QUEUE_NAME).build();
	}


	@Bean
	public Binding bindDlxQueueExchange(@Qualifier("dlx_queue") Queue queue, @Qualifier("dlx_exchange") Exchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("dlx.#").noargs();
	}

	@Bean("exchange")
	public Exchange exchange(){
		return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
	}

	@Bean("queue")
	public Queue queue(){
		return QueueBuilder.durable(QUEUE_NAME)
				.deadLetterExchange(DLX_EXCHANGE_NAME)
				.deadLetterRoutingKey("dlx.hehe")
				.maxLength(5)
				.ttl(10000) //设置整个队列消息过期时间
				.build();
	}

	// 将来配置类中会定义很多交换机和队列，以及它们之间的绑定关系，所以通过名字注入来区分
	@Bean
	public Binding bindQueueExchange(@Qualifier("queue") Queue queue, @Qualifier("exchange") Exchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("example.#").noargs();
	}

	@PostConstruct
	public void init(){
		// 确认模式
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if (ack){
					System.out.println("成功接收消息"+cause);
				}else {
					System.out.println("接收失败");
					//做一些处理，保证消息再次发送能够成功
				}
			}
		});
		//设置交换机处理失败消息的模式，默认丢弃，设置后会将消息退回给发送端，触发ReturnCallBack
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int i, String s, String s1, String s2) {
				System.out.println("该方法被执行了，说明消息没被发送到queue");
			}
		});
		// for (int i = 0; i < 10; i++) {
		    //消息后置处理
			// MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
			// 	@Override
			// 	public Message postProcessMessage(Message message) throws AmqpException {
			// 		message.getMessageProperties().setExpiration("5000");
			// 		return message;
			// 	}
			// };

			//发送消息
			// rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "example.info","springboot hello",messagePostProcessor);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "example.info","springboot hello");
		// }


	}
}
