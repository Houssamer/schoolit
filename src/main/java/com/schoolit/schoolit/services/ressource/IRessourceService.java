package com.schoolit.schoolit.services.ressource;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Ressource;
import com.schoolit.schoolit.models.Texte;
import com.schoolit.schoolit.models.Video;

import java.util.Collection;

public interface IRessourceService {
    Collection<Ressource> getRessources();
    Ressource getRessource(Long id);
    Collection<Ressource> getRessourceParCours(Cours cours);
    Ressource getRessourceParTitre(String titre);
    void ajouterVideo(Video video);
    void ajouterTexte(Texte texte);
    void modifierRessource(Ressource ressource);
    void deleteRessource(Long id);
}
