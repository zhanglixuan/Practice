package com.example.order.service.impl;

import com.example.feign.client.UserClient;
import com.example.feign.pojo.User;
import com.example.order.mapper.OrderMapper;
import com.example.order.pojo.Order;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    // @Autowired
    // private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    @Override
    public Order queryOrderById(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        //写ip是硬编码，现在不能这么写了
        //改成服务名称，剩下的交个eureka客户端去处理。
        //所以order也要引入eureka客户端，然后注册服务信息
        // String url = "http://userservice/users/"+order.getUserId();
        // User user = restTemplate.getForObject(url, User.class);
        User user = userClient.queryById(order.getUserId());
        order.setUser(user);
        // 1.查询订单
        return order;
    }
}
