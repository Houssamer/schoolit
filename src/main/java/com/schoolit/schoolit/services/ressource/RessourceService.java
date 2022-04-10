package com.schoolit.schoolit.services.ressource;

import com.schoolit.schoolit.Exception.RessourceException;
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
        return ressourceRepo.findAll();
    }

    @Override
    public Ressource getRessource(Long id) {
        return  ressourceRepo.getById(id);
    }

    @Override
    public Collection<Ressource> getRessourceParCours(Cours cours) {
        return ressourceRepo.findRessourcesByCours(cours);
    }

    @Override
    public Ressource getRessourceParTitre(String titre) {
        return ressourceRepo.findByTitre(titre);
    }

    @Override
    public void ajouterVideo(Video video) {
        boolean exist = ressourceRepo.existsById(video.getId());
        if (exist) {
            throw new RessourceException("Ressource deja existe");
        } else {
            ressourceRepo.save(video);
        }
    }

    @Override
    public void ajouterTexte(Texte texte) {
        boolean exist = ressourceRepo.existsById(texte.getId());
        if (exist) {
            throw new RessourceException("Ressource deja existe");
        } else {
            ressourceRepo.save(texte);
        }
    }

    @Override
    public void modifierRessource(Ressource ressource) {
        boolean exist = ressourceRepo.existsById(ressource.getId());
        if (exist) {
            ressourceRepo.save(ressource);
        } else {
            throw new RessourceException();
        }
    }

    @Override
    public void deleteRessource(Long id) {
        boolean exist = ressourceRepo.existsById(id);
        if (exist) {
            ressourceRepo.deleteById(id);
        } else {
            throw new RessourceException();
        }
    }
}
