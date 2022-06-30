package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.Admin;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Utilisateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.registration.RegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;

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
    public ResponseEntity<?> getFormateurParId(@PathVariable Long id) {
            return ResponseEntity.ok().body((Formateur) utilisateurService.getUtilisateur(id));
    }

    @GetMapping("/formations/{id}")
    public ResponseEntity<?> getFormationsCrees(@RequestParam("id") Long id) {
            return ResponseEntity.ok().body(utilisateurService.getFormationsCrees(id));
    }

    @GetMapping("/disabled")
    public ResponseEntity<Collection<Formateur>> getFormateurNonVerifie() {
        return ResponseEntity.ok().body(utilisateurService.getFormateurNonVerifie());
    }

    @PostMapping("/enable")
    public ResponseEntity<?> enableFormateur(@RequestBody RegistrationRequest r) {
        utilisateurService.deleteApprenant(r.getEmail());
        return ResponseEntity.ok(utilisateurService.unlockUtilisateur(r.getEmail()));
    }

    @PostMapping("/find")
    public ResponseEntity<Formateur> getFormateurParEmail(@RequestBody RegistrationRequest r) {
        return ResponseEntity.ok().body(utilisateurService.getFormateurByEmail(r.getEmail()));
    }

    @PostMapping("/add")
    public ResponseEntity<?> ajouterFormateur(@RequestBody RegistrationRequest request) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/formateur/add").toUriString());
        try {
            registrationService.registerFormateur(request);
            return ResponseEntity.created(uri).body("Formateur bien ajoute");
        } catch (UtilisateurException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifierFormateur(@RequestBody Formateur formateur) {
            utilisateurService.modifierUtilisateur(formateur);
            return ResponseEntity.ok("formateur modifier");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFormateur(@RequestParam("id") Long id) {
            utilisateurService.deleteFormateur(id);
            return ResponseEntity.ok("formateur supprime");
    }
}
