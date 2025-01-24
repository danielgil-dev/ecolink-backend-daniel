package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

}
