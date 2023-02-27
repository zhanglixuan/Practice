package com.example.order.service;

import com.example.order.pojo.Order;

public interface OrderService {

    public Order queryOrderById(Long orderId);
}
