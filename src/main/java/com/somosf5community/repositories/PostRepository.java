package com.somosf5community.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somosf5community.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
