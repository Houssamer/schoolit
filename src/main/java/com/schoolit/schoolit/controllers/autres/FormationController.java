package com.schoolit.schoolit.controllers.autres;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;
import com.schoolit.schoolit.services.formation.FormationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/formation")
public class FormationController {
    private final FormationService formationService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public FormationController(FormationService formationService,
                               UtilisateurService utilisateurService) {
        this.formationService = formationService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Formation>> getFormations() {
        return ResponseEntity.ok().body(formationService.getFormations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormation(@PathVariable Long id) {
        return ResponseEntity.ok().body(formationService.getFormation(id));
    }

    @PostMapping("/formateur")
    public ResponseEntity<Collection<Formation>> getFormationParFormateur(@RequestBody Long id) {
        Formateur formateur = (Formateur) utilisateurService.getUtilisateur(id);
        return ResponseEntity.ok().body(formationService.getFormationsParFormateur(formateur));
    }

    @PostMapping("/specialite")
    public ResponseEntity<Collection<Formation>> getFormationParSpecialite(@RequestBody String specialite) {
        return ResponseEntity.ok().body(formationService.getFormationsParSpecialite(specialite));
    }

    @PostMapping("/nom")
    public ResponseEntity<Formation> getFormationParNom(@RequestBody String nom) {
        return ResponseEntity.ok().body(formationService.getFormationParNom(nom));
    }

    @PostMapping("/add")
    public ResponseEntity<Formation> ajouterFormation(@RequestBody Formation formation) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/formation/add").toUriString());

        return ResponseEntity.created(uri).body(formationService.ajouterFormation(formation));
    }

    @PostMapping("/update")
    public ResponseEntity<?> modifierFormation(@RequestBody Formation formation) {
        formationService.modifierFormation(formation);
        return ResponseEntity.ok("formation modifiee");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
        return ResponseEntity.ok("formation supprimee");
    }
}
