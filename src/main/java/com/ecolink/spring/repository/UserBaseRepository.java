package com.ecolink.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.UserBase;
import java.util.List;


public interface UserBaseRepository extends JpaRepository<UserBase, Long> {
    Optional<UserBase> findByName(String name);

    Optional<UserBase> findByEmail(String email);
    Optional<UserBase> findByEmailOrName(String email, String username);

    boolean existsByName(String user);

    boolean existsByEmail(String email);

}
