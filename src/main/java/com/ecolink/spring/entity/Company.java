package com.ecolink.spring.entity;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDate;
>>>>>>> 92f4000ac3cf9a765d6ca912a6f612e4aae61175

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company extends UserBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Company (String name, String email, Long nivel ){
        this.name = name;
        this.nivel = 0L;
        this.userType = UserType.COMPANY;
        this.email = email;
 
    }

   
}
