package com.schoolit.schoolit.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "formations")
@Data @AllArgsConstructor @NoArgsConstructor
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Champ requis")
    private String nom;

    @NotEmpty(message = "Champ requis")
    private String specialite;

    @Column(nullable = true)
    private int evaluation;

    @ManyToMany
    private Collection<Apprenant> apprenants;

    @ManyToOne
    private Formateur formateur;

    @OneToMany(mappedBy = "formation")
    private Collection<Cours> cours;

}
