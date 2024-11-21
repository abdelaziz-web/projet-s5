package org.projet.gatwayservice.controller;

import org.projet.gatwayservice.dto.AuthResponse;
import org.projet.gatwayservice.dto.LoginRequest;
import org.projet.gatwayservice.dto.RegisterRequest;
import org.projet.gatwayservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.registerUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getDateOfBirth(),
                request.getGender()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}