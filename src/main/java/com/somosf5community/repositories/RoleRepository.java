package com.somosf5community.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somosf5community.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{ 
    
}
