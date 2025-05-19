package com.ecolink.spring.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "odsList")
    @JsonIgnore
    private List<Startup> startups;

    @ManyToMany(mappedBy = "odsList")
    @JsonIgnore
    private List<Company> companies;

    @ManyToMany(mappedBy = "preferences")
    @JsonIgnore
    private List<Client> userPreferences;

    @ManyToMany(mappedBy = "odsList")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    private String image;
    
    public Ods(String name,  String image) {
        this.name = name;
        this.startups = new ArrayList<>();
        this.image = image;
    }

    //Metodos helpers STARTUP
    public void addStartup(Startup startup) {
        this.startups.add(startup);
    }
    public void addPreference(Client client) {
        this.userPreferences.add(client);
    }

    //POST
    public void addPost(Post post){
        this.posts.add(post);
    }

    public void removePost(Post post){
        this.posts.remove(post);
    }

    //Metodos helpers COMPANY
    public void addCompany(Company company) {
        this.companies.add(company);
    }

    public void removeCompany(Company company) {
        this.companies.remove(company);
    }
}
