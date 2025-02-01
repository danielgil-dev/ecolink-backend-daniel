package com.ecolink.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.UserBase;

public interface UserBaseRepository extends JpaRepository<UserBase, Long> {
    Optional<UserBase> findByName(String name);

    Optional<UserBase> findByEmail(String email);

    boolean existsByName(String user);

    boolean existsByEmail(String email);

}
