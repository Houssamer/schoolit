package com.schoolit.schoolit.controllers.autres;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;
import com.schoolit.schoolit.services.cours.CoursService;
import com.schoolit.schoolit.services.formation.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/cours")
public class CoursController {
    private final CoursService coursService;
    private final FormationService formationService;

    @Autowired
    public CoursController(CoursService coursService, FormationService formationService) {
        this.coursService = coursService;
        this.formationService = formationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Cours>> getAllCours() {
        return ResponseEntity.ok().body(coursService.getAllCours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCours(@PathVariable Long id) {
        return ResponseEntity.ok().body(coursService.getCours(id));
    }

    @PostMapping("/formation")
    public ResponseEntity<Collection<Cours>> getCoursParFormation(@RequestBody Long id) {
        Formation formation = formationService.getFormation(id);
        return ResponseEntity.ok().body(coursService.getCoursParFormation(formation));
    }

    @PostMapping("/titre")
    public ResponseEntity<Cours> getFormationParNom(@RequestBody String titre) {
        return ResponseEntity.ok().body(coursService.getCoursParTitre(titre));
    }

    @PostMapping("/add")
    public ResponseEntity<Cours> ajouterCours(@RequestBody Cours cours) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/cours/add").toUriString());

        return ResponseEntity.created(uri).body(coursService.ajouterCours(cours));
    }

    @PostMapping("/update")
    public ResponseEntity<?> modifierCours(@RequestBody Cours cours) {
        coursService.modifierCours(cours);
        return ResponseEntity.ok("cours modifie");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFormation(@PathVariable Long id) {
        coursService.deleteCours(id);
        return ResponseEntity.ok("cours supprime");
    }
}
