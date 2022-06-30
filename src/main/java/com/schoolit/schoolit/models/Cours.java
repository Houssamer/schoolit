package com.schoolit.schoolit.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "cours")
@Data @AllArgsConstructor @NoArgsConstructor
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Champ requis")
    private String titre;

    @ManyToOne
    private Formation formation;

}
