package com.schoolit.schoolit.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotEmpty(message = "Champ requis")
    private String titre;

    @ManyToOne
    private Cours cours;

}
