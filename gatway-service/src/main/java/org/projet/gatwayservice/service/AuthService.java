package org.projet.gatwayservice.service;

import org.projet.gatwayservice.model.User;
import org.projet.gatwayservice.repository.UserRepository;
import org.projet.gatwayservice.security.jwt.JwtTokenProvider;
import org.projet.gatwayservice.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthResponse registerUser(String firstName, String lastName, String email,
                                     String password, String dateOfBirth, String gender) {
        // Check if user already exists
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already taken!");
        }

        // Create new user
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setDateOfBirth(dateOfBirth);
        user.setGender(gender);
        user.setCreatedAt(LocalDateTime.now().toString());
        user.setUpdatedAt(LocalDateTime.now().toString());

        User savedUser = userRepository.save(user);
        String token = jwtTokenProvider.generateToken(savedUser.getEmail());

        return new AuthResponse(token, savedUser);
    }

    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new AuthResponse(token, user);
    }
}