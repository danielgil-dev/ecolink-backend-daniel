package com.ecolink.spring.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Startup extends UserBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @OneToMany(mappedBy = "startup")
    List<Proposal> proposals;
    
    @OneToMany(mappedBy = "startup")
    List<Product> products;
    
    
    public void addChallenge(Proposal proposal) {
    	this.proposals.add(proposal);
    }
    
    public void addProduct(Product product) {
    	this.products.add(product);
    }
}
