package com.ecolink.spring.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client extends UserBase {

    @ManyToMany(mappedBy = "clients")
    private List<Mission> missions;

    public Client(String name, List<Ods> odsList, String email, String description) {
        this.name = name;
        this.level = 0L;
        this.userType = UserType.CLIENT;
        this.email = email;
    }

    public void addMision(Mission mission) {
        this.missions.add(mission);
    }

        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_CLIENT");
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}