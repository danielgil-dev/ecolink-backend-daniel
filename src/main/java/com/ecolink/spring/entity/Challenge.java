package com.ecolink.spring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Company company;
    
    @ManyToOne
    private Ods ods;
    private String title;
    private String description;
    private BigDecimal budget;
    private LocalDateTime startDate;
    
    @OneToMany(mappedBy = "challenge")
    List<Proposal> proposals;
    
    public void addChallenge(Proposal proposal) {
    	this.proposals.add(proposal);
    }
    
}
