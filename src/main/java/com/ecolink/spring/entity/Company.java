package com.ecolink.spring.entity;

import jakarta.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class Company extends UserBase {
    public Company(String name, String email, Long level) {
        this.name = name;
        this.level = (level != null) ? level : 0L;
        this.userType = UserType.COMPANY;
        this.email = email;

    }
}
