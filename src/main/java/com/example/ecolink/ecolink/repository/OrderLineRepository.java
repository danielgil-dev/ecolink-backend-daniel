package com.example.ecolink.ecolink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecolink.ecolink.entity.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

}
