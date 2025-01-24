package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.OrderLineRepository;

@Service
public class OrderLineService {

        @Autowired
        private OrderLineRepository orderLineRepository;
}
