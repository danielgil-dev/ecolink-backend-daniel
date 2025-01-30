package com.ecolink.spring.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
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

    @OneToMany(mappedBy = "company") //Agrego la relacion de que una empresa tenga una lista de retos de los que ha creado
    private List<Challenge> challenges = new ArrayList<>();

    //Metodos Helpers
    public void addChallenge(Challenge challenge){

        this.challenges.add(challenge);
        if(challenge.getCompany() == null){
            challenge.setCompany(this);
        }
    }

    public void removeChallenge(Challenge challenge){
        this.challenges.remove(challenge);
        challenge.setCompany(null);
    }


    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    
}
