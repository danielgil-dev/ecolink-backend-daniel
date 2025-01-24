package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

}
