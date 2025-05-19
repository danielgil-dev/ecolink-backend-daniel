package com.ecolink.spring.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.modelmapper.internal.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToMany(mappedBy = "company")
    List<Challenge> challenges;
    
    @ManyToMany
    @JoinTable(name = "company_ods", joinColumns = @JoinColumn(name = "id_company"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    List<Ods> odsList;
    //  @JoinTable(name = "startup_ods", joinColumns = @JoinColumn(name = "id_startup"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    @Column
    private String description;
    private String location;
    private Double compability;


    public Company(String name, String email, String description, String image,String location, List<Ods> odsList) {
        this.name = name;
        this.level = 0L;
        this.userType = UserType.COMPANY;
        this.email = email;
        this.description = description;
        this.challenges = new ArrayList<>();
        this.status = Status.PENDING;
        this.imageUrl = image;
        this.location = location;
        this.odsList = odsList;
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
