package com.ecolink.spring.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company extends UserBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Company(Long id, String name, String email, String password, Long nivel, LocalDate registerDate ){
        this.userType = userType.COMPANY;
        this.email = email;
        this.password = password;
        this.nivel = 0L;
        this.registerDate = registerDate;
    }
}
