package org.projet.userprofile.repositories;

import org.projet.userprofile.Entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface profilerepo extends JpaRepository<UserProfile, Long> {


}
