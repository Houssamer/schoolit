package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotEmpty(message = "Champ requis")
    @Column(unique = true)
    @Email
    private String email;

    @NotEmpty(message = "Champ requis")
    private String password;

    @NotEmpty(message = "Champ requis")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "Champ requis")
    @Past
    private LocalDate DateNaissance;

}
