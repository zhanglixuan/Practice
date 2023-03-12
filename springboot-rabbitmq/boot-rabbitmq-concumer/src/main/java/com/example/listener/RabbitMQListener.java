package com.example.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 张丽璇
 * @date 2023/3/12
 */
@Component
public class RabbitMQListener {

	@RabbitListener(queues = "boot_topic_queue")
	public void testTopicListener(Channel channel, Message message) throws InterruptedException, IOException {
		// Thread.sleep(1000);
		//获取消息
		System.out.println(new String(message.getBody()));
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		try {
			System.out.println("业务处理...");
			int a = 3 / 0;
			//手动签收
			channel.basicAck(deliveryTag, true);
		} catch (Exception e) {
			//拒接签收
			System.out.println("业务异常...");
			channel.basicNack(deliveryTag,true,false);
		}
	}
}
