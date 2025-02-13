package com.ecolink.spring.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company extends UserBase {

    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "company")
    List<Challenge> challenges;

    public Company(String name, String email, Long level, String description) {
        this.name = name;
        this.level = (level != null) ? level : 0L;
        this.userType = UserType.COMPANY;
        this.email = email;
        this.description = description;
        this.challenges = new ArrayList<>();
        this.status = Status.PENDING;
        // ! Eliminar mas tarde
        this.setVerified(true);
    }

      //Metodos Helpers
    public void addChallenge(Challenge challenge){

        if(!this.challenges.contains(challenge)){
            this.challenges.add(challenge);
        }
        if(challenge.getCompany() == null){
            challenge.setCompany(this);
        }
    }

    public void removeChallenge(Challenge challenge){
        this.challenges.remove(challenge);
        challenge.setCompany(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_COMPANY");
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    
}
