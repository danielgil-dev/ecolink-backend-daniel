package com.ecolink.spring.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends UserBase {

    @ManyToMany(mappedBy = "clients")
    private List<Mission> missions;

    public Admin(String name, String email, String password) {
        this.name = name;
        this.level = 0L;
        this.userType = UserType.ADMIN;
        this.email = email;
        this.password = password;
    }

    public void addMision(Mission mission) {
        this.missions.add(mission);
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
