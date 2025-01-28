package com.ecolink.spring.entity;

import jakarta.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class Company extends UserBase {
    public Company(String name, String email, Long nivel) {
        this.name = name;
        this.nivel = 0L;
        this.userType = UserType.COMPANY;
        this.email = email;

    }
}
