package com.schoolit.schoolit.controllers.autres;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Ressource;
import com.schoolit.schoolit.models.Texte;
import com.schoolit.schoolit.models.Video;
import com.schoolit.schoolit.services.ressource.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Collection<Ressource>> getAllRessources() {
        return ResponseEntity.ok().body(ressourceService.getRessources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ressource> getRessource(@PathVariable Long id) {
        return ResponseEntity.ok().body(ressourceService.getRessource(id));
    }

    @PostMapping("/cours")
    public ResponseEntity<Collection<Ressource>> getRessourceParCours(@RequestBody Cours cours) {
        return ResponseEntity.ok().body(ressourceService.getRessourceParCours(cours));
    }

    @PostMapping("/titre")
    public ResponseEntity<Ressource> getRessourceParTtire(@RequestBody String titre) {
        return ResponseEntity.ok().body(ressourceService.getRessourceParTitre(titre));
    }

    @PostMapping("/add/video")
    public ResponseEntity<Video> ajouterVideo(@RequestBody Video video) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/ressource/add/video").toUriString());
        return ResponseEntity.created(uri).body(ressourceService.ajouterVideo(video));
    }

    @PostMapping("/add/text")
    public ResponseEntity<Texte> ajouterTexte(@RequestBody Texte texte) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/ressource/add/text").toUriString()
        );
        return ResponseEntity.created(uri).body(ressourceService.ajouterTexte(texte));
    }

    @PutMapping("/update/video")
    public ResponseEntity<?> modifierVideo(Video video) {
        ressourceService.modifierRessource(video);
        return ResponseEntity.ok("video modifiee");
    }

    @PutMapping("/update/text")
    public ResponseEntity<?> modifierText(Texte text) {
        ressourceService.modifierRessource(text);
        return ResponseEntity.ok("texte modifie");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRessource(@PathVariable Long id) {
        ressourceService.deleteRessource(id);
        return ResponseEntity.ok("deleted");
    }

}
