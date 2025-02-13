package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderStatus;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order getCart(UserBase user) {
        return repository.findByUserAndStatus(user, OrderStatus.CART);
    }

    public Order findByUserAndStatus(UserBase user, OrderStatus status) {
        return repository.findByUserAndStatus(user, status);
    }

    public boolean existsByUserAndStatus(UserBase user, OrderStatus status) {
        return repository.existsByUserAndStatus(user, status);
    }

    public void save(Order order) {
        repository.save(order);
    }
}
