package com.somosf5community.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.somosf5community.exception.ProfileNotFoundException;
import com.somosf5community.models.Profile;
import com.somosf5community.repositories.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileService implements BaseService<Profile>  {

    private ProfileRepository profileRepository;
    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Profile findById(Long id) {
        return profileRepository.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException("Profile nor found with id: " + id));
    }

    @Override
    @Transactional
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Profile profileDelete = findById(id);
        profileRepository.deleteById(profileDelete.getId());
    }

    @Transactional
    public Profile updateProfile(Long id, Profile profileDetails){
        Profile profile = findById(id);
        profile.setName(profileDetails.getName());
        profile.setSurname(profileDetails.getSurname());
        profile.setLocation(profileDetails.getLocation());
        profile.setLinkedin(profileDetails.getLinkedin());
        profile.setGithub(profileDetails.getGithub());
        return save(profile);
    }

}
