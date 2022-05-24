package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "formateurs")
@Data @AllArgsConstructor @NoArgsConstructor
public class Formateur extends Utilisateur {

    private Integer nbrFormations;

    private boolean enabled = false;

    @Transient
    private final Role[] roles = {Role.Formatteur, Role.Apprenant};

    @OneToMany(mappedBy = "formateur")
    private Collection<Formation> formationsCrees;

    public Formateur(String nom,
                     String prenom,
                     String email,
                     LocalDate dateNaissance,
                     String password) {
        super(nom, prenom, email, dateNaissance, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        });
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<Formation> getFormationsSuivies() {
        return null;
    }

}
