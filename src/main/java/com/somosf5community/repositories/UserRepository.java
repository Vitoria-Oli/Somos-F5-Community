package com.somosf5community.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somosf5community.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    
}

