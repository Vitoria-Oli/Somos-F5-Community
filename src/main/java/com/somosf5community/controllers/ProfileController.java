package com.somosf5community.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosf5community.models.Profile;
import com.somosf5community.services.ProfileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public Profile getfindById(@PathVariable Long id) {
        return profileService.findById(id);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile savedProfile = profileService.save(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @PutMapping("/update/{id}")
    public Profile updateprofile(@PathVariable Long id, @RequestBody Profile profileDetails) {
        return profileService.updateProfile(id, profileDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        profileService.deleteById(id);
        return ResponseEntity.ok("Profile deleted successfully");
    }

    // @GetMapping("addContact/{id}")
    // public Profile addContact(@PathVariable Long id){
    //     profileService.addContact(id);
    //     return null;
    // }

}
