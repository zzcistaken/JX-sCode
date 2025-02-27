package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderSimple;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    public List<OrderSimple> getAllOrderSimple() {
        return orderRepository.findAllOrderSimple();
    }

    public List<Order> getOrdersByDingdanh(String dingdanh) {
        return orderRepository.findByDingdanh(dingdanh);
    }

    public Order getOrdersByDingdanhChanpdma(String dingdanh, String chanpdma) {
        List<Order> list = orderRepository.findOneByDingdanhChanpdma(dingdanh, chanpdma);
        if(list == null || list.size() == 0) {
            return null;
        }else {
            return list.get(0);
        }
    }

    public List<OrderSimple> getOrderSimpleByDingdanh(String dingdanh) {
        return orderRepository.findOrderSimpleByDingdanh(dingdanh);
    }

    public int saveOrder(Order order) {
        System.out.println("start saveOrder");
        return orderRepository.insert(order);
    }

    public int updateOrder(Order order) {
        System.out.println("start saveOrder");
        return orderRepository.update(order);
    }

    public int saveOrderSimple(OrderSimple order) {
        System.out.println("start saveOrderSimple");
        return orderRepository.insert(order);
    }
}