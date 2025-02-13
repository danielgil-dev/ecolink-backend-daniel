package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderStatus;
import com.ecolink.spring.entity.UserBase;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserAndStatus(UserBase user, OrderStatus status);

    boolean existsByUserAndStatus(UserBase user, OrderStatus status);
}