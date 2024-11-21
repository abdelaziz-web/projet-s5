package org.projet.gatwayservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    private String gender;

    // Using @Column(updatable = false) for creation timestamp
    @Column(updatable = false)
    private String createdAt;

    private String updatedAt;
}