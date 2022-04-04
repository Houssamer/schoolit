package com.schoolit.schoolit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "formateurs")
@Data @AllArgsConstructor @NoArgsConstructor
public class Formateur extends Utilisateur {

    private Integer nbrFormations;

    @OneToMany(mappedBy = "formateur")
    private Collection<Formation> formationsCrees;

}
