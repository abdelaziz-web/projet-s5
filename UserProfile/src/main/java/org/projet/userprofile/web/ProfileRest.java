package org.projet.userprofile.web;

import org.projet.userprofile.Entities.UserProfile;
import org.projet.userprofile.repositories.profilerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileRest {

    @Autowired
    private profilerepo repo;

    @GetMapping("/{id}")
    public Optional<UserProfile> getProfileById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<UserProfile> getAllProfiles() {
        return repo.findAll();
    }
}