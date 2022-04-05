package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "formateurs")
@Data @AllArgsConstructor @NoArgsConstructor
public class Formateur extends Utilisateur {

    private Integer nbrFormations;

    private boolean enabled = false;

    @Transient
    private Role role = Role.Formatteur;

    @OneToMany(mappedBy = "formateur")
    private Collection<Formation> formationsCrees;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(this.role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
