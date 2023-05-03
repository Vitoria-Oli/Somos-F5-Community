package com.somosf5community.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somosf5community.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
