package com.schoolit.schoolit.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "videos")
@Data @AllArgsConstructor @NoArgsConstructor
public class Video extends Ressource{

    @NotEmpty(message = "Champ requis")
    private String lien;

    @Column(nullable = true)
    private String description;

}
