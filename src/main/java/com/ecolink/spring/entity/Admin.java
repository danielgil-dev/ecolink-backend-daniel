package com.ecolink.spring.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends UserBase {


    public Admin(String name, String email, String password) {
        this.name = name;
        this.level = 0L;
        this.userType = UserType.ADMIN;
        this.email = email;
        this.password = password;
        this.isVerified = true;
        this.imageUrl = "admin.jpg";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_ADMIN");
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
