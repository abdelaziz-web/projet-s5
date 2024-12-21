package org.projet.auth.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String gender;
}