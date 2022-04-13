package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.registration.RegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/formateur")
public class FormateurController {
    private final UtilisateurService utilisateurService;
    private final RegistrationService registrationService;

    @Autowired
    public FormateurController(UtilisateurService utilisateurService,
                               RegistrationService registrationService) {
        this.utilisateurService = utilisateurService;
        this.registrationService = registrationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Formateur>> getAllFormateur() {
        return ResponseEntity.ok().body(utilisateurService.getFormateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formateur> getFormateurParId(@PathVariable Long id) {
        return ResponseEntity.ok().body((Formateur) utilisateurService.getUtilisateur(id));
    }

    @GetMapping("/disbaled")
    public ResponseEntity<Collection<Formateur>> getFormateurNonVerifie() {
        return ResponseEntity.ok().body(utilisateurService.getFormateurNonVerifie());
    }

    @GetMapping("/enable/{id}")
    public ResponseEntity<?> enableFormateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.enableCompte(id));
    }

    @PostMapping("/find")
    public ResponseEntity<Formateur> getFormateurParEmail(String email) {
        return ResponseEntity.ok().body((Formateur) utilisateurService.getUtilisateurByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<Formateur> ajouterFormateur(@RequestBody RegistrationRequest request) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/formateur/add").toUriString());
        return ResponseEntity.created(uri).body(registrationService.registerFormateur(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifierFormateur(@RequestBody Formateur formateur) {
        utilisateurService.modifierUtilisateur(formateur);
        return ResponseEntity.ok("formateur modifier");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFormateur(@PathVariable Long id) {
        utilisateurService.deleteFormateur(id);
        return ResponseEntity.ok("formateur supprime");
    }
}
