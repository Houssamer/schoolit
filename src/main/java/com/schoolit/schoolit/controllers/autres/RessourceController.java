package com.schoolit.schoolit.controllers.autres;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Ressource;
import com.schoolit.schoolit.models.Texte;
import com.schoolit.schoolit.models.Video;
import com.schoolit.schoolit.services.ressource.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/ressource")
public class RessourceController {
    private final RessourceService ressourceService;

    @Autowired
    public RessourceController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    @GetMapping("/all")
    public Collection<Ressource> getAllRessources() {
        return ressourceService.getRessources();
    }

    @GetMapping("/{id}")
    public Ressource getRessource(@PathVariable Long id) {
        return ressourceService.getRessource(id);
    }

    @PostMapping("/cours")
    public Collection<Ressource> getRessourceParCours(@RequestBody Cours cours) {
        return ressourceService.getRessourceParCours(cours);
    }

    @PostMapping("/titre")
    public Ressource getRessourceParTtire(@RequestBody String titre) {
        return ressourceService.getRessourceParTitre(titre);
    }

    @PostMapping("/add/video")
    public void ajouterVideo(@RequestBody Video video) {
        ressourceService.ajouterVideo(video);
    }

    @PostMapping("/add/text")
    public void ajouterTexte(@RequestBody Texte texte) {
        ressourceService.ajouterTexte(texte);
    }

    @PutMapping("/update/video")
    public void modifierVideo(Video video) {
        ressourceService.modifierRessource(video);
    }

    @PutMapping("/update/text")
    public void modifierText(Texte text) {
        ressourceService.modifierRessource(text);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRessource(@PathVariable Long id) {
        ressourceService.deleteRessource(id);
    }

}
