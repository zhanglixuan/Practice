package com.example;

import com.example.config.RabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 张丽璇
 * @date 2023/3/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void testTopicMessage(){
		//发送消息
		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "example.info","springboot hello");
	}

	@Test
	public void testDLX(){
		//确认模式
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
		for (int i = 0; i < 5; i++) {
			// MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
			// 	@Override
			// 	public Message postProcessMessage(Message message) throws AmqpException {
			// 		message.getMessageProperties().setExpiration("10000");
			// 		return message;
			// 	}
			// };

			//发送消息
			// rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "example.info","springboot hello",messagePostProcessor);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "example.info","springboot hello");
		}
	}


}
