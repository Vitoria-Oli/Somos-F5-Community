package com.somosf5community.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somosf5community.models.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    
}
