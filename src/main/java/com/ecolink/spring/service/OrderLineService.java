package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderLine;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.repository.OrderLineRepository;

@Service
public class OrderLineService {

        @Autowired
        private OrderLineRepository repository;

        public OrderLine findByOrderAndProduct(Order order, Product product) {
                return repository.findByOrderAndProduct(order, product);
        }

        public void save(OrderLine orderLine) {
            repository.save(orderLine);
        }
}
