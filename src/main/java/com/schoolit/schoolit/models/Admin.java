package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
public class Admin extends Utilisateur{
    @Transient
    private final Role[] roles = {Role.Admin, Role.Apprenant, Role.Formatteur};

    public Admin(String nom,
                     String prenom,
                     String email,
                     String username,
                     LocalDate dateNaissance,
                     String password) {
        super(nom, prenom, email, username, dateNaissance, password);
    }

    @Override
    public Collection<Formation> getFormationsCrees() {
        return null;
    }

    @Override
    public Collection<Formation> getFormationsSuivies() {
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
