package com.schoolit.schoolit.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "textes")
@Data @AllArgsConstructor @NoArgsConstructor
public class Texte extends Ressource{

    @NotEmpty(message = "Champ requis")
    private String contenu;

    @ManyToOne
    private Cours cours;

}
