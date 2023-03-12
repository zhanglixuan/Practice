package com.example;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author 张丽璇
 * @date 2023/3/11
 */
public class SpringQueueListener implements MessageListener {
	//当配置文件被加载后，rabbit监听器中这个监听就会找到这个监听器类
	//然后执行onMessage回调方法
	@Override
	public void onMessage(Message message) {
		//打印消息
		System.out.println("SpringQueue:"+new String(message.getBody()));
	}
}
