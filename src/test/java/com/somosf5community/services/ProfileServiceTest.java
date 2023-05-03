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

import com.somosf5community.controllers.ProfileController;
import com.somosf5community.exception.ProfileNotFoundException;
import com.somosf5community.models.Profile;
import com.somosf5community.repositories.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {


    @InjectMocks
    ProfileService service;

    @Mock
    ProfileRepository repository;

  

    private Profile profile;
    private Profile profile2;

    @BeforeEach
    void setup(){
        profile = new Profile(1L,"Nombre 1", "Apellido 1", "Gijón", "GitHub 1", "Linkedin 1", null, null);
        profile2 = new Profile(2L,"Nombre 2", "Apellido 2", "Gijón", "GitHub 2", "Linkedin 2", null, null);
    }

    @Test
    public void findById_ShouldReturnProfile_WhenProfileExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(profile));
        Profile currentProfile = service.findById(1L);

        assertEquals(1L,currentProfile.getId());
        assertEquals("Nombre 1",currentProfile.getName());
       }

       @Test
       public void findById_ShouldThrowException_WhenProfileDoesNotExist() {
           when(repository.findById(1L)).thenReturn(Optional.empty());
    
           assertThrows(ProfileNotFoundException.class, () -> service.findById(1L));
       }

       @Test 
       public void findAll_shouldReturnAllProfiles() {
              when(repository.findAll()).thenReturn(List.of(profile, profile2));
              List<Profile> profiles = service.findAll();
     
              assertThat(profiles).hasSize(2);
              assertThat(profiles.get(0).getId()).isEqualTo(1L);
              assertThat(profiles.get(1).getId()).isEqualTo(2L);
              assertThat(profiles.get(0).getName()).isEqualTo("Nombre 1");
              assertThat(profiles.get(1).getName()).isEqualTo("Nombre 2");
       }

    @Test
       public void save_shouldSaveProfile() {
           when(repository.save(profile)).thenReturn(profile);
           Profile savedProfile = service.save(profile);
    
           assertThat(savedProfile.getId()).isEqualTo(1L);
           assertThat(savedProfile.getName()).isEqualTo("Nombre 1");
       }

    @Test
        public void deleteById_shouldDeleteProfile() {
              when(repository.findById(1L)).thenReturn(Optional.of(profile));
              service.deleteById(1L);
        }

    @Test 
        public void deleteById_shouldThrowException_WhenProfileDoesNotExist() {
              when(repository.findById(1L)).thenReturn(Optional.empty());
              assertThrows(ProfileNotFoundException.class, () -> service.deleteById(1L));
        }

    @Test
        public void updateProfile_shouldUpdateProfile() {
                when(repository.findById(1L)).thenReturn(Optional.of(profile));
                when(repository.save(profile)).thenReturn(profile);
            Profile updateProfile = service.updateProfile(1L, profile);
        
                assertThat(updateProfile.getId()).isEqualTo(1L);
                assertThat(updateProfile.getName()).isEqualTo("Nombre 1");
            }

    @Test       
        public void updateProfile_shouldThrowException_WhenProfileDoesNotExist() {
                when(repository.findById(1L)).thenReturn(Optional.empty());
        
                assertThrows(ProfileNotFoundException.class, () -> service.updateProfile(1L, profile));
            }
}
