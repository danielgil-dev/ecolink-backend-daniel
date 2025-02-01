package com.ecolink.spring.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Startup extends UserBase {
    @OneToMany(mappedBy = "startup")
    List<Proposal> proposals;

    @OneToMany(mappedBy = "startup")
    List<Product> products;

    @ManyToMany
    @JoinTable(name = "startup_ods", joinColumns = @JoinColumn(name = "id_startup"), inverseJoinColumns = @JoinColumn(name = "id_ods"))
    private List<Ods> odsList;

    private String description;

    public Startup(String name, List<Ods> odsList, String email, String description) {
        this.name = name;
        this.level = 0L;
        this.odsList = odsList;
        this.userType = UserType.STARTUP;
        this.email = email;
        this.description = description;
    }

    public void addChallenge(Proposal proposal) {
        this.proposals.add(proposal);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addOds(Ods ods) {
        this.odsList.add(ods);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_STARTUP");
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}