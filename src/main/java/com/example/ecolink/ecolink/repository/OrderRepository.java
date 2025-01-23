package com.example.ecolink.ecolink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
