package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "apprenants")
@Data @AllArgsConstructor @NoArgsConstructor
public class Apprenant extends Utilisateur {

    private Integer nbrFormationsSuivies;

    @ManyToMany(mappedBy = "apprenants")
    private Collection<Formation> formationsSuivies;

}
