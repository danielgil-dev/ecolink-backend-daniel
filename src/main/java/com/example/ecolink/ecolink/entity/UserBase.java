package com.example.ecolink.ecolink.entity;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public abstract class UserBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    userType userType;
    String name;
    String email;
    String password;
    Long nivel;
    LocalDate registerDate;
}
