package com.schoolit.schoolit.services.ressource;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Ressource;
import com.schoolit.schoolit.models.Texte;
import com.schoolit.schoolit.models.Video;
import com.schoolit.schoolit.repos.RessourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class RessourceService implements IRessourceService {
    private final RessourceRepo ressourceRepo;

    @Autowired
    public RessourceService(RessourceRepo ressourceRepo) {
        this.ressourceRepo = ressourceRepo;
    }

    @Override
    public Collection<Ressource> getRessources() {
        return null;
    }

    @Override
    public Collection<Ressource> getRessourceParCours(Cours cours) {
        return null;
    }

    @Override
    public Ressource getRessourceParTitre(String titre) {
        return null;
    }

    @Override
    public Video ajouterVideo(Video video) {
        return null;
    }

    @Override
    public Texte ajouterTexte(Texte texte) {
        return null;
    }

    @Override
    public String modifierRessource(Ressource ressource) {
        return null;
    }

    @Override
    public void deleteRessource(Long id) {

    }
}
