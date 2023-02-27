package com.example.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 张丽璇
 * @date 2023/2/26
 */
@Configuration
public class SpringConfig {
	/**
	 * Spring提供的一个工具，能够发起各种各样的http请求。底层对http请求进行了封装
	 * @return
	 */
	@Bean
	@LoadBalanced //负载均衡算法（按照一定的算法顺序触发）
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	/**
	 * 修改负载均衡规则
	 * 作用范围：全局。不管访问的是哪个服务
	 */
	// @Bean
	// public IRule rule(){
	// 	return new RandomRule();
	// }
}
