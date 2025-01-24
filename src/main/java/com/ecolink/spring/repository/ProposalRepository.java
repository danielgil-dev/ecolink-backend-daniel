package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    
}
