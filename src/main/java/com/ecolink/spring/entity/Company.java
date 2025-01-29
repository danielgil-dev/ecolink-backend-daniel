package com.ecolink.spring.entity;

import jakarta.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class Company extends UserBase {
    private String description;
    public Company(String name, String email, Long level, String description) {
        this.name = name;
        this.level = (level != null) ? level : 0L;
        this.userType = UserType.COMPANY;
        this.email = email;
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
