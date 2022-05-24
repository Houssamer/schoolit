package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "apprenants")
@Data @AllArgsConstructor @NoArgsConstructor

public class Apprenant extends Utilisateur {

    private Integer nbrFormationsSuivies;
    @Transient
    private final Role[] roles = {Role.Apprenant};

    @ManyToMany(mappedBy = "apprenants")
    private Collection<Formation> formationsSuivies;

    public Apprenant(String nom,
                     String prenom,
                     String email,
                     LocalDate dateNaissance,
                     String password) {
        super(nom, prenom, email, dateNaissance, password);
    }

    @Override
    public Collection<Formation> getFormationsCrees() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        });
        return authorities;
    }

}
