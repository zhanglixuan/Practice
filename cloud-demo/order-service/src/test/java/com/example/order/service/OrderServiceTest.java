package com.example.order.service;

import com.example.order.OrderApplication;
import com.example.order.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 张丽璇
 * @date 2023/2/26
 */
@SpringBootTest(classes = OrderApplication.class)
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Test
	public void testGetById(){
		Order order = orderService.queryOrderById(101L);
		System.out.println(order);
	}
}
