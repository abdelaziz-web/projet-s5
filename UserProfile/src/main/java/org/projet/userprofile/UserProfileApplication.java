package org.projet.userprofile;

import org.projet.userprofile.Entities.UserProfile;
import org.projet.userprofile.repositories.profilerepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UserProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(profilerepo profilerepo) {

        return args -> {
            List<UserProfile> userProfiles = List.of(
                    UserProfile.builder()
                            .firstName("abdelaziz")
                            .lastName("achendid")
                            .email("abdelaziz@gmail.com")
                            .createdAt(LocalDateTime.now())  // Added required fields
                            .updatedAt(LocalDateTime.now())
                            .build(),  // Added .build()
                    UserProfile.builder()
                            .firstName("hassan")
                            .lastName("hassan")
                            .email("hassan@gmail.com")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build()  // Added .build()
            );

            profilerepo.saveAll(userProfiles);  // Save the profiles to database
        };



    }
}
