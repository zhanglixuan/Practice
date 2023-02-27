package com.example.order.dao;

import com.example.order.OrderApplication;
import com.example.order.mapper.OrderMapper;
import com.example.order.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 张丽璇
 * @date 2023/2/26
 */
@SpringBootTest(classes = OrderApplication.class)
public class OrderDaoTest {
	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void testGetById(){
		Order order = orderMapper.selectById(101L);
		System.out.println(order);
	}
}
