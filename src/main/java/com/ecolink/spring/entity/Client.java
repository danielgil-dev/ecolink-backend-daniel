package com.ecolink.spring.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Client extends UserBase {

    @ManyToMany(mappedBy = "clients")
    private List<Mission> missions = new ArrayList<>();

    public Client(String name, List<Ods> odsList, String email, String description) {
        this.name = name;
        this.level = 0L;
        this.userType = UserType.CLIENT;
        this.email = email;
    }

    @ManyToMany
    @JoinTable(name = "user_preferences", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    private List<Ods> preferences;

    public void addMision(Mission mission) {
        this.missions.add(mission);
        mission.addClient(this);
        
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