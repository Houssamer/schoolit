package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Champ requis")
    @Column(unique = true)
    @Email
    private String email;

    @NotEmpty(message = "Champ requis")
    private String password;

    @NotEmpty(message = "Champ requis")
    private String nom;

    @NotEmpty(message = "Champ requis")
    private String prenom;

    private LocalDate dateNaissance;

    public Utilisateur(String nom,
                       String prenom,
                       String email,
                       String username,
                       LocalDate dateNaissance,
                       String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public abstract Collection<Formation> getFormationsCrees();

    public abstract Collection<Formation> getFormationsSuivies();
}
