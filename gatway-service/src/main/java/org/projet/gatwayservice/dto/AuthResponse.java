package org.projet.gatwayservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.projet.gatwayservice.model.User;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private User user;  // Added to match frontend expectations
}