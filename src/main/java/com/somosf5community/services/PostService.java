package com.somosf5community.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.somosf5community.exception.PostNotFoundException;
import com.somosf5community.models.Post;
import com.somosf5community.models.User;
import com.somosf5community.repositories.PostRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService implements BaseService<Post> {
    
    private PostRepository postRepository;
    private UserService userService;

    @Override
    @Transactional
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        return postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

    }

    @Override
    @Transactional
    public Post save(Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByUsername(currentUsername);
        post.setIdProfile(user.getId());
        post.setProfile(user.getProfile());
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Post postDelete = findById(id);
        postRepository.deleteById(postDelete.getId());
    }
    
    @Transactional
    public Post updatePost(Long id, Post postDetails){
        Post post = findById(id);
        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        post.setDate(postDetails.getDate());
        post.setProfile(postDetails.getProfile());
        return save(post);
    }

    
    
}
