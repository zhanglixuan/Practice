package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 张丽璇
 * @date 2023/3/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class producerTest {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void testHelloWorld(){
		//发送消息
		rabbitTemplate.convertAndSend("spring_queue","spring hello world");
	}

	@Test
	public void testFanout(){
		rabbitTemplate.convertAndSend("spring_fanout_exchange","", "spring fanout...");
	}

	@Test
	public void testTopic(){
		rabbitTemplate.convertAndSend("spring_topic_exchange", "example.hello.test", "spring topic...");
	}

	@Test
	public void testDirect(){
		rabbitTemplate.convertAndSend("spring_direct_exchange","info", "spring direct...");
	}
}
