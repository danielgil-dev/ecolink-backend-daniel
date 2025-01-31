package com.ecolink.spring.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Company extends UserBase {
    private String description;

    public Company(String name, String email, Long level, String description) {
        this.name = name;
        this.level = (level != null) ? level : 0L;
        this.userType = UserType.COMPANY;
        this.email = email;
        this.description = description;
    }
}
