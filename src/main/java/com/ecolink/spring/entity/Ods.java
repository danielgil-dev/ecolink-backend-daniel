package com.ecolink.spring.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "odsList")
    private List<Startup> startups;


    public Ods(String name) {
        this.name = name;
        this.startups = new ArrayList<>();
    }

    public void addStartup(Startup startup) {
        this.startups.add(startup);
    }
}
