package org.projet.auth.Service;

import lombok.RequiredArgsConstructor;
import org.projet.auth.Dto.LoginRequest;
import org.projet.auth.Dto.RegisterRequest;
import org.projet.auth.Dto.AuthResponse;
import org.projet.auth.Entity.User;
import org.projet.auth.Repository.UserRepository;
import org.projet.auth.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        try {

            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Email already exists"
                );
            }

            // Create new user
            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setDateOfBirth(request.getDateOfBirth());
            user.setGender(request.getGender());

            userRepository.save(user);

            System.out.println(user.getEmail());

            String token = jwtUtil.generateToken(user.getEmail());
            return new AuthResponse(token, "User registered successfully");
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error during registration: " + e.getMessage()
            );
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "User not found"
                    ));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid password"
                );
            }

            String token = jwtUtil.generateToken(user.getEmail());
            return new AuthResponse(token, "Login successful");
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error during login: " + e.getMessage()
            );
        }
    }
}