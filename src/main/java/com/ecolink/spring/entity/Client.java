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
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "client")
    private List<ClientMission> clientMissions = new ArrayList<>();

    public Client(String name, List<Ods> odsList, String email) {
        this.name = name;
        this.level = 0L;
        this.userType = UserType.CLIENT;
        this.email = email;
    }

    @ManyToMany
    @JoinTable(name = "user_preferences", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    private List<Ods> preferences;

    public void addMission(Mission mission) {

        ClientMission clientMission = new ClientMission(this, mission);
        this.clientMissions.add(clientMission);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_CLIENT");
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }
}