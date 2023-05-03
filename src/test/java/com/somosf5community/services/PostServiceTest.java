package com.somosf5community.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.somosf5community.controllers.PostController;
import com.somosf5community.exception.PostNotFoundException;
import com.somosf5community.models.Post;
import com.somosf5community.repositories.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {


    @InjectMocks
    PostService service;

    @Mock
    PostRepository repository;

    private Post post;
    private Post post2;

    @BeforeEach
    void setup(){
        post = new Post(1L,"Titulo 1",null,"Description 1", "Imagen 1", null, null);
        post2 = new Post(2L,"Titulo 2",null,"Description 2", "Imagen 2", null, null);
    }

    @Test
    public void findById_ShouldReturnPost_WhenPostExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(post));
        Post currentPost = service.findById(1L);

        assertEquals(1L,currentPost.getId());
        assertEquals("Titulo 1",currentPost.getTitle());
       }

       @Test
       public void findById_ShouldThrowException_WhenPostDoesNotExist() {
           when(repository.findById(1L)).thenReturn(Optional.empty());
    
           assertThrows(PostNotFoundException.class, () -> service.findById(1L));
       }

       @Test 
       public void findAll_shouldReturnAllPosts() {
              when(repository.findAll()).thenReturn(List.of(post, post2));
              List<Post> posts = service.findAll();
     
              assertThat(posts).hasSize(2);
              assertThat(posts.get(0).getId()).isEqualTo(1L);
              assertThat(posts.get(1).getId()).isEqualTo(2L);
              assertThat(posts.get(0).getTitle()).isEqualTo("Titulo 1");
              assertThat(posts.get(1).getTitle()).isEqualTo("Titulo 2");
       }

    @Test
        public void deleteById_shouldDeletePost() {
              when(repository.findById(1L)).thenReturn(Optional.of(post));
              service.deleteById(1L);
        }

    @Test 
        public void deleteById_shouldThrowException_WhenPostDoesNotExist() {
              when(repository.findById(1L)).thenReturn(Optional.empty());
              assertThrows(PostNotFoundException.class, () -> service.deleteById(1L));
        }

    @Test       
        public void updatePost_shouldThrowException_WhenPostDoesNotExist() {
                when(repository.findById(1L)).thenReturn(Optional.empty());
        
                assertThrows(PostNotFoundException.class, () -> service.updatePost(1L, post));
            }
}