package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderLine;
import com.ecolink.spring.entity.Product;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

    OrderLine findByOrderAndProduct(Order order, Product product);
    
    OrderLine findByIdAndOrder(Long id, Order order);
}
