package com.ecolink.spring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Company company;
    
    @ManyToMany
    @JoinTable(name = "challenge_ods", joinColumns = @JoinColumn(name = "id_startup"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    private List<Ods> odsList;

    private String title;
    private String description;
    private BigDecimal budget;
    private LocalDateTime endDate;
    
    @OneToMany(mappedBy = "challenge")
    List<Proposal> proposals;
    

    public Challenge(Company company ,String title, String description, BigDecimal budget, LocalDateTime endDate,List<Ods> odsList){
        this.company = company;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.endDate = endDate;
        this.odsList = odsList;
    }
    public void addChallenge(Proposal proposal) {
    	this.proposals.add(proposal);
    }
    
}
